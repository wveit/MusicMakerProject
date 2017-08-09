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
    public void setNumMeasures(int numMeasures){mNumMeasures = numMeasures;}

    public int getBeatsPerMeasure() {
        return mBeatsPerMeasure;
    }
    public void setBeatsPerMeasure(int bpm){mBeatsPerMeasure = bpm;}

    public int getNumTones() {
        return mToneList.size();
    }

    public Tone getTone(int index) {
        return mToneList.get(index);
    }

    public int findToneAt(Note note, int measure, int beat) { //Find int of Tone in arrayList

        for (int i = 0; i < mToneList.size(); i++) {
            Tone tone = mToneList.get(i);
            if(tone.getNote() != note)
                continue;

            int startCode = (tone.getStartMeasure()-1) * mBeatsPerMeasure + tone.getStartBeat()-1;
            int endCode = startCode + tone.getLengthInBeats() - 1;
            int targetCode = (measure-1) * mBeatsPerMeasure + beat-1;

            if(targetCode >= startCode && targetCode <= endCode)
                return i;

//            int beatLength = mToneList.get(i).getLengthInBeats();
//            int beatStart = mToneList.get(i).getStartBeat();
//            int measureStart = mToneList.get(i).getStartMeasure();
//            int markerStart = ((measureStart * 4) + beatStart);
//            int markerEnd = markerStart + beatLength;
//            int beatMarker = ((4 * measure) + beat);
//
//            if (beatMarker >= markerStart && beatMarker <= markerEnd) {
//                return i;

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
