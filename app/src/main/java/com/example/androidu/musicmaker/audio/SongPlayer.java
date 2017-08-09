package com.example.androidu.musicmaker.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.PlacedLoop;
import com.example.androidu.musicmaker.model.Song;
import com.example.androidu.musicmaker.model.Tone;


public class SongPlayer {

    private PlayStatus mPlayStatus = PlayStatus.STOPPED;
    private boolean mRepeatFlag = false;
    private Song mSong = null;
    private PCMGenerator mGen = new PCMGenerator();

    public void setSong(Song song){
        mSong = song;
        if(song != null) {
            mGen.setBeatsPerMeasure(song.getBeatsPerMeasure());
        }
    }

    public void setRepeat(boolean flag){
        mRepeatFlag = flag;
    }
    public boolean repeatIsSet(){
        return mRepeatFlag;
    }

    public void setCurrentMeasure(int measure){mGen.goToMeasureAndBeat(measure, getCurrentBeat());}
    public void setCurrentBeat(int beat){mGen.goToMeasureAndBeat(getCurrentMeasure(), beat);}


    public int getCurrentMeasure(){ return mGen.currentMeasure(); }
    public int getCurrentBeat(){ return mGen.currentBeat(); }

    public void play(){

        if(mPlayStatus != PlayStatus.STOPPED){
            mPlayStatus = PlayStatus.PLAYING;
            return;
        }

        mPlayStatus = PlayStatus.PLAYING;

        new Thread(){
            @Override
            public void run() {
                prepareGenerator();

                int minBufferSize = AudioTrack.getMinBufferSize(mGen.getSampleRate(), AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, mGen.getSampleRate(), AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize, AudioTrack.MODE_STREAM);
                audioTrack.play();

                short[] buffer = new short[minBufferSize];
                while(mPlayStatus != PlayStatus.STOPPED){
                    if(mGen.currentMeasure() > mSong.getNumMeasures()){
                        SongPlayer.this.stop();
                    }
                    if(mPlayStatus == PlayStatus.PLAYING){
                        mGen.fillBuffer(buffer);
                        audioTrack.write(buffer, 0, buffer.length);
                    }
                    else{
                        try {
                            sleep(10);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                }
                audioTrack.stop();
                audioTrack.release();

            }
        }.start();
    }

    private void prepareGenerator(){
        if(mSong == null)
            return;

        mGen.setTempo(mSong.getTempo());
        mGen.clearTones();

        /* add tones to mGen */
        for(int i = 0; i < mSong.getNumPlacedLoops(); i++){
            PlacedLoop ploop = mSong.getPlacedLoop(i);
            Loop loop = ploop.getLoop();

            for(int j = 0; j < loop.getNumTones(); j++){
                Tone tone = loop.getTone(j);
                int measure = tone.getStartMeasure() + ploop.getStartMeasure() - 1;
                int beat = tone.getStartBeat() + ploop.getStartBeat() - 1;
                if(beat > mSong.getBeatsPerMeasure()){
                    measure += 1;
                    beat -= mSong.getBeatsPerMeasure();
                }
                Tone newTone = new Tone(tone.getNote(), tone.getInstrument(), measure, beat, tone.getLengthInBeats());
                mGen.addTone(newTone);
            }
        }
    }

    public void pause(){
        if(mPlayStatus == PlayStatus.PLAYING){
            mPlayStatus = PlayStatus.PAUSED;
        }
    }

    public void stop(){

        Log.d("song player", "stop called");
        mPlayStatus = PlayStatus.STOPPED;
    }

    public boolean isPlaying(){return mPlayStatus == PlayStatus.PLAYING;}
    public boolean isPaused(){return mPlayStatus == PlayStatus.PAUSED;}
    public boolean isStopped(){return mPlayStatus == PlayStatus.STOPPED;}

    public void setVolume(int volume){mGen.setVolume(volume);}
    public int getVolume(){return mGen.getVolume();}

    public void setTempo(int tempo){mGen.setTempo(tempo);}
    public int getTempo(){return mGen.getTempo();}
}


