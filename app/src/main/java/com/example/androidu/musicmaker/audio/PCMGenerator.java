package com.example.androidu.musicmaker.audio;

import android.util.Log;

import com.example.androidu.musicmaker.audio.instrument.*;
import com.example.androidu.musicmaker.model.Tone;

import java.util.ArrayList;




public class PCMGenerator {

    private int mSampleRate = 44100;
    private int mTempo = 60;
    private int mBeatsPerMeasure = 4;
    private int mVolume = 1000;
    private ArrayList<Tone> mToneList = new ArrayList<Tone>();
    private int mSamplePosition = 0;

    public void setVolume(int volume){
        mVolume = volume;
    }
    public int getVolume(){
        return mVolume;
    }

    public void setSampleRate(int sampleRate){
        mSampleRate = sampleRate;
    }
    public int getSampleRate(){
        return mSampleRate;
    }

    public void setTempo(int tempo){
        mTempo = tempo;
    }
    public int getTempo(){
        return mTempo;
    }

    public void setBeatsPerMeasure(int beatsPerMeasure){
        mBeatsPerMeasure = beatsPerMeasure;
    }
    public int getBeatsPerMeasure(){
        return mBeatsPerMeasure;
    }

    public void addTone(Tone tone){
        mToneList.add(tone);
    }
    public void clearTones(){mToneList.clear();}

    public void goToSamplePosition(int samplePosition){
        mSamplePosition = samplePosition;
    }
    public void goToMeasureAndBeat(int measure, int beat){
        int numCompletedBeats = (measure - 1) * mBeatsPerMeasure + beat - 1;
        mSamplePosition = numCompletedBeats * samplesPerBeat();
    }
    public int getSamplePosition(){
        return mSamplePosition;
    }
    public int currentMeasure(){
        return currentBeatCode() / 4 + 1;
    }
    public int currentBeat(){
        return currentBeatCode() % 4 + 1;
    }
    public int currentBeatCode(){
        int samplesPerBeat = mSampleRate * 60 / mTempo;
        return mSamplePosition / samplesPerBeat;
    }


    public void fillBuffer(short[] buffer){

        // Zero out the buffer
        for(int i = 0; i < buffer.length; i++)
            buffer[i] = 0;


        // Figure out the following:
        //  + number of samples that will be written
        //  + number of samples per beat
        //  + starting and ending beat codes for this fill operation
        //  + number of samples left to do for first beat of this fill operation
        //  + number of samples that will be done for last beat of this fill operation
        int numBufferSamples = buffer.length;
        int samplesPerBeat = samplesPerBeat();

        int beatsCompleted = mSamplePosition / samplesPerBeat;
        int startBeatCode = beatsCompleted;

        int nextSampleAfterFill = mSamplePosition + numBufferSamples;
        int endBeatCode = (nextSampleAfterFill - 1) / samplesPerBeat;

        int numSamplesDoneForFirstBeat = mSamplePosition % samplesPerBeat;
        int numSamplesNeededForFirstBeat = samplesPerBeat - numSamplesDoneForFirstBeat;

        int numSamplesDoneForLastBeat = nextSampleAfterFill % samplesPerBeat;


        // For each tone in mToneList:
        //  + calculate tone's starting and ending beat codes
        //  + get the tone's frequency
        //  + check if the tone should play during this fill
        //     - if not, continue to next tone
        //  + calculate the starting sample and ending sample for the tone
        //  + write the tone waveshape to the buffer between starting sample and ending sample
        for(int i = 0; i < mToneList.size(); i++) {
            Tone tone = mToneList.get(i);
            float freq = tone.getNote().freq();
            InstrumentFunction instrumentFunction = tone.getInstrument().getFunction();

            int toneStartBeatCode = tone.getStartBeat() - 1 + (tone.getStartMeasure() - 1) * mBeatsPerMeasure;
            int toneEndBeatCode = toneStartBeatCode + tone.getLengthInBeats() - 1;
            if(toneEndBeatCode < startBeatCode || toneStartBeatCode > endBeatCode)
                continue;

            int startIndex, endIndex;

            if(toneStartBeatCode <= startBeatCode)
                startIndex = 0;
            else
                startIndex = numSamplesNeededForFirstBeat + (toneStartBeatCode - startBeatCode) * samplesPerBeat;

            if(toneEndBeatCode >= endBeatCode)
                endIndex = buffer.length - 1;
            else
                endIndex = buffer.length - 1 - numSamplesDoneForLastBeat - (endBeatCode - 1 - toneEndBeatCode) * samplesPerBeat;


            for(int j = startIndex; j <= endIndex; j++){
                buffer[j] += mVolume * instrumentFunction.f(2 * Math.PI * (mSamplePosition + j) / mSampleRate * freq);
            }

        }

        // Update mSamplePosition
        mSamplePosition = nextSampleAfterFill;

        Log.d(
                "PCMGenerator", "Measure: " + currentMeasure() + "   Beat: " + currentBeat() +
                "   SamplePosition: " + mSamplePosition + "   SamplesPerBeat: " + samplesPerBeat
        );
    }


    private int samplesPerBeat(){
        return mSampleRate * 60 / mTempo;
    }


}
