package com.example.androidu.musicmaker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlacedLoop {
    private Loop mLoop;
    private int mStartMeasure;
    private int mStartBeat;
    private int mRowNumnber;
    private int id;

    public PlacedLoop(Loop loop, int startMeasure, int startBeat, int rowNumber){
        mLoop = loop;
        this.mStartMeasure = startMeasure;
        this.mStartBeat = startBeat;
        this.mRowNumnber = rowNumber;
    }

    @JsonIgnore
    public Loop getLoop(){return mLoop;} //ask about this methods

    public int getStartMeasure(){return mStartMeasure;}
    public int getStartBeat(){return mStartBeat;}
    public int getRowNumber(){return mRowNumnber;}
    public int getId() {return id;}

    // Note: If we use an index for the loop instead of loop reference, this class can be immutable

}

