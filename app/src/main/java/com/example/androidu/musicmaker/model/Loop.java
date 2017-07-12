package com.example.androidu.musicmaker.model;

import java.util.ArrayList;

public class Loop {
    private int mNumMeasures;
    private int mBeatsPerMeasure;
    private ArrayList<Tone> mToneList;

    public Loop(int numMeasures, int beatsPerMeasure){}

    public int getNumMeasures(){ return 0; }
    public int getBeatsPerMeasure(){ return 0; }

    public int getNumTones(){ return 0; }
    public Tone getTone(int index){ return null; }
    public int findToneAt(Note note, int measure, int beat){ return 0; }
    public void addTone(Tone tone){}
    public void removeTone(int index){}

    public void setVolume(int volume){}
    public int getVolume(){return 0;}

    public void setTempo(int tempo){}
    public int getTempo(){return 0;}
}
