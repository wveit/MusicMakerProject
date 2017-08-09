package com.example.androidu.musicmaker.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Song {
    private int mNumMeasures;
    private int mBeatsPerMeasure;
    private String mName = "";


    private ArrayList<Loop> mLoopList = new ArrayList<Loop>();

    private ArrayList<PlacedLoop> mPlacedLoopList = new ArrayList<PlacedLoop>();
    private int mTempo = 60;


    public Song(int numMeasures, int beatsPerMeasure){
        this.mNumMeasures = numMeasures;
        this.mBeatsPerMeasure = beatsPerMeasure;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public int getNumMeasures(){
        return mNumMeasures;
    }

    public void setNumMeasures(int numMeasures){
        mNumMeasures = numMeasures;
    }

    public int getBeatsPerMeasure(){
        return mBeatsPerMeasure;
    }

    public void setBeatsPerMeasure(int bpm){
        mBeatsPerMeasure = bpm;
    }

    public void addLoop(Loop loop){
        mLoopList.add(loop);
        loop.setId(mloopID);
        mloopID++;
    }

    public int getNumLoops(){
        return mLoopList.size();
    }

    public Loop getLoop(int index){
        return mLoopList.get(index);
    }

    public void removeLoop(int index){
        mLoopList.remove(index);
    }

    public void addPlacedLoop(PlacedLoop loop){
        mPlacedLoopList.add(loop);
    }

    public int findPlacedLoopAt(int rowNumber, int measure, int beat){

        for(int i = 0; i < mPlacedLoopList.size(); i++){
            PlacedLoop ploop = mPlacedLoopList.get(i);
            if(rowNumber == ploop.getRowNumber()){
                int startingBeatCode = ploop.getStartBeat() + (ploop.getStartMeasure() - 1) * 4;

                int endingBeatCode = startingBeatCode + ploop.getLoop().getNumMeasures() * 4 - 1;

                int targetBeatCode = (measure - 1) * 4 + beat;

                if(targetBeatCode >= startingBeatCode && targetBeatCode <= endingBeatCode){
                    return i;
                }
            }
        }
        return -1;
    }

    public int getNumPlacedLoops(){
        return mPlacedLoopList.size();
    }

    public PlacedLoop getPlacedLoop(int index){
        return mPlacedLoopList.get(index);
    }

    public void removePlacedLoop(int index){
        mPlacedLoopList.remove(index);
    }

    public ArrayList<Loop> getmLoopList() {
        return mLoopList;
    }

    public ArrayList<PlacedLoop> getmPlacedLoopList() {
        return mPlacedLoopList;
    }

    public void setTempo(int tempo){
        mTempo = tempo;
    }

    public int getTempo(){
        return mTempo;
    }


    /////////////////////
    //
    //  This mLoopId stuff is so that when a new loop is added, it can have an auto
    //     generated id
    //
    ////////////////////
    private int mloopID = 0;
    public int getLoopID(){
        return mloopID;
    }

}
