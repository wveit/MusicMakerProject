package com.example.androidu.musicmaker.model;

public class PlacedLoop {
    private Loop mLoop;
    private int mStartMeasure;
    private int mStartBeat;
    private int mRowNumnber;


    public PlacedLoop(Loop loop, int startMeasure, int startBeat, int rowNumber){
        mLoop = loop;
        this.mStartMeasure = startMeasure;
        this.mStartBeat = startBeat;
        this.mRowNumnber = rowNumber;
    }
    public Loop getLoop(){return mLoop;} //ask about this methods
    public int getStartMeasure(){return mStartMeasure;}
    public int getStartBeat(){return mStartBeat;}
    public int getRowNumber(){return mRowNumnber;}

    // Note: If we use an index for the loop instead of loop reference, this class can be immutable

}

