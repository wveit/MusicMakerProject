package com.example.androidu.musicmaker.model;

/**
 * Created by pujasudip on 8/4/17.
 */

public class BeatAndMeasure {
    int beat;
    int meausre;

    public BeatAndMeasure(int beat, int measure){
        this.beat = beat;
        this.meausre = measure;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public int getMeausre() {
        return meausre;
    }

    public void setMeausre(int meausre) {
        this.meausre = meausre;
    }
}
