package com.example.androidu.musicmaker.audio;

import android.util.Log;

import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Tone;

import java.util.ArrayList;

/**
 * Created by wilbert on 8/2/17.
 */




public class PCMGenerator {

    private int mSampleRate = 44100;
    private int mTempo = 60;
    private int mNextSample = 0;
    private int mNumMeasures = -1;
    private int mBeatsPerMeasure = 4;
    private boolean mRepeatFlag = false;
    private int volume = 400;
    private ArrayList<Tone> mToneList = new ArrayList<Tone>();


    public void setSampleRate(int sampleRate){
        mSampleRate = sampleRate;
    }

    public void setTempo(int tempo){
        mTempo = tempo;
    }

    public void resetPosition(){
        mNextSample = 0;
    }

    public void setRepeat(boolean repeatFlag){
        mRepeatFlag = repeatFlag;
    }

    public void setNumMeasures(int numMeasures){
        mNumMeasures = numMeasures;
    }

    public void setBeatsPerMeasure(int beatsPerMeasure){
        mBeatsPerMeasure = beatsPerMeasure;
    }

    public void addTone(Tone tone){
        mToneList.add(tone);
    }

    public int currentMeasure(){
        return currentBeatCode() / 4 + 1;
    }

    public int currentBeat(){
        return currentBeatCode() % 4 + 1;
    }

    public int currentBeatCode(){
        int samplesPerBeat = mSampleRate * 60 / mTempo;
        return mNextSample / samplesPerBeat;
    }

    /*
     *
     * This method is not verified functioning yet!!!
     *
     */
    public void fillBuffer(short[] buffer){
        if(buffer == null)
            throw new NullPointerException("PCMGenerator.fillBuffer(short[] buffer): buffer cannot be null");

        // Zero out the buffer
        for(int i = 0; i < buffer.length; i++)
            buffer[i] = 0;


        // Figure out the following:
        //  + number of samples that will be written
        //  + samplesPerBeat
        //  + starting and ending beat codes for this fill operation
        //  + number of samples left to do for first beat of this fill operation
        //  + number of samples that will be done for last beat of this fill operation
        int numBufferSamples = buffer.length;
        int samplesPerBeat = mSampleRate * 60 / mTempo;

        int beatsCompleted = mNextSample / samplesPerBeat;
        int startBeatCode = beatsCompleted;

        int nextSampleAfterFill = mNextSample + numBufferSamples;
        int endBeatCode = (nextSampleAfterFill - 1) / samplesPerBeat;

        int numSamplesDoneForFirstBeat = mNextSample % samplesPerBeat;
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
                buffer[j] += volume * Math.sin(2 * Math.PI * (mNextSample + j) / mSampleRate * freq);
            }

        }

        // Update mNextSample
        mNextSample = nextSampleAfterFill;
    }


}
