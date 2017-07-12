package com.example.androidu.musicmaker.model;

public class Tone {
    public Tone(Note note, Instrument instrument, int measure, int beat, int lengthInBeats){}

    public Note getNote(){ return null; }
    public Instrument getInstrument(){ return null; }
    public int getStartMeasure(){ return 0; }
    public int getStartBeat(){ return 0; }
    public int getLengthInBeats(){ return 0; }

    // Note: I think this class should be immutable
}
