package com.example.androidu.musicmaker.model;

import java.util.ArrayList;

public class Song {
    private int mNumMeasures;
    private int mBeatsPerMeasure;
    private ArrayList<Loop> mLoopList;
    private ArrayList<PlacedLoop> mPlacedLoopList;

    public Song(int numMeasures, int beatsPerMeasure){}

    public int getNumMeasures(){ return 0; }
    public int getBeatsPerMeasure(){ return 0; }

    public void addLoop(Loop loop){}
    public int getNumLoops(){return 0;}
    public Loop getLoop(int index){return null;}
    public void removeLoop(int index){}

    public void addPlacedLoop(PlacedLoop loop){}
    public int findPlacedLoopAt(int rowNumber, int measure, int beat){return 0;}
    public int getNumPlacedLoops(){return 0;}
    public PlacedLoop getPlacedLoop(int index){return null;}
    public void removePlacedLoop(int index){}

    public void setVolume(int volume){}
    public int getVolume(){return 0;}

    public void setTempo(int tempo){}
    public int getTempo(){return 0;}
}
