package com.example.androidu.musicmaker.audio;

import com.example.androidu.musicmaker.model.Song;


public class SongPlayer {
    public void setSong(Song song){}

    public void setCurrentMeasure(int measure){}
    public void setCurrentBeat(int beat){}

    public int getCurrentMeasure(){ return 0; }
    public int getCurrentBeat(){ return 0; }

    public void play(){}
    public void pause(){}
    public void stop(){}

    public boolean isPlaying(){return false;}
    public boolean isPaused(){return false;}
    public boolean isStopped(){return false;}

    public void setVolume(int volume){}
    public int getVolume(){return 0;}

    public void setTempo(int tempo){}
    public int getTempo(){return 0;}
}


/*

    Issues and Questions:
    * Should there also be a getSong() method?

 */
