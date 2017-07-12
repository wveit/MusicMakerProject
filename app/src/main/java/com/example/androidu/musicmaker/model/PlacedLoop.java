package com.example.androidu.musicmaker.model;

public class PlacedLoop {
    private Loop mLoop;
    private int mStartMeasure;
    private int mStartBeat;
    private int mRowNumnber;


    public PlacedLoop(Loop loop, int startMeasure, int startBeat, int rowNumber){}
    public Loop getLoop(){return null;}
    public int getStartMeasure(){return 0;}
    public int getStartBeat(){return 0;}
    public int getRowNumber(){return 0;}

    // Note: If we use an index for the loop instead of loop reference, this class can be immutable

}

