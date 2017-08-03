package com.example.androidu.musicmaker.model;

public class Tone {
    public Tone(Note note, Instrument instrument, int measure, int beat, int lengthInBeats) {
    }

    private Note mNote;
    private Instrument mInstrument;
    private int mMeasure;
    private int mBeat;
    private int mLengthInBeats;

    public Note getNote() {
        return mNote;
    }

    public Instrument getInstrument() {
        return mInstrument;
    }

    public int getStartMeasure() {
        return mMeasure;
    }

    public int getStartBeat() {
        return mBeat;
    }

    public int getLengthInBeats() {
        return mLengthInBeats;
    }

    // Note: I think this class should be immutable
}
