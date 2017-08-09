package com.example.androidu.musicmaker.model;

import java.util.ArrayList;

public class Loop {
    private int mNumMeasures;
    private int mBeatsPerMeasure;
    private int mTempo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name = "";

    public ArrayList<Tone> getmToneList() {
        return mToneList;
    }

    private ArrayList<Tone> mToneList;

    public Loop(int numMeasures, int beatsPerMeasure) {
        if (numMeasures <= 0) {
            throw new BadInputException("Loop.Loop(): Number of Measures needs to be greater than 0.");
        } else {
            mNumMeasures = numMeasures;
        }

        if (beatsPerMeasure <= 0) {
            throw new BadInputException("Loop.Loop(): Number of beats per measure needs to be greater than 0.");
        } else {
            mBeatsPerMeasure = 4;
        }

        mToneList = new ArrayList<Tone>();
        this.setId(-1);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getNumMeasures() {
        return mNumMeasures;
    }

    public int getBeatsPerMeasure() {
        return mBeatsPerMeasure;
    }

    public int getNumTones() {
        return mToneList.size();
    }

    public Tone getTone(int index) {
        return mToneList.get(index);
    }

    public int findToneAt(Note note, int measure, int beat) { //Find int of Tone in arrayList
        for (int i = 0; i < mToneList.size(); i++) { //Loop through Tone List
            int beatLength = mToneList.get(i).getLengthInBeats(); //Entire Beat Length
            int beatStart = mToneList.get(i).getStartBeat(); //Starting beat in measure
            int measureStart = mToneList.get(i).getStartMeasure(); // Starting measure
            int markerStart = ((measureStart * 4) + beatStart); //Starting CheckPoint
            int markerEnd = markerStart + beatLength; //Ending CheckPoint
            int beatMarker = ((4 * measure) + beat);

            if (beatMarker >= markerStart && beatMarker <= markerEnd) {
                return i;
            }
        }
        return -1;
    }

    public void addTone(Tone tone) {
        mToneList.add(tone);
    }

    public void removeTone(int index) {
        mToneList.remove(index);
    }

    public void setTempo(int tempo) {
        mTempo = tempo;
    }

    public int getTempo() {
        return mTempo;
    }
}
