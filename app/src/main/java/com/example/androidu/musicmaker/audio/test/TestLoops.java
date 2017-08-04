package com.example.androidu.musicmaker.audio.test;


import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Tone;

public class TestLoops {

    public static Loop twinkleLoop(){
        Loop loop = new Loop(4, 4);

        Instrument instrument = Instrument.PIANO;
        loop.addTone(new Tone(Note.C_5, instrument, 1, 1, 1));
        loop.addTone(new Tone(Note.C_5, instrument, 1, 2, 1));
        loop.addTone(new Tone(Note.G_5, instrument, 1, 3, 1));
        loop.addTone(new Tone(Note.G_5, instrument, 1, 4, 1));
        loop.addTone(new Tone(Note.A_5, instrument, 2, 1, 1));
        loop.addTone(new Tone(Note.A_5, instrument, 2, 2, 1));
        loop.addTone(new Tone(Note.G_5, instrument, 2, 3, 2));

        instrument = Instrument.TRUMPET;
        loop.addTone(new Tone(Note.F_5, instrument, 3, 1, 1));
        loop.addTone(new Tone(Note.F_5, instrument, 3, 2, 1));
        loop.addTone(new Tone(Note.E_5, instrument, 3, 3, 1));
        loop.addTone(new Tone(Note.E_5, instrument, 3, 4, 1));
        loop.addTone(new Tone(Note.D_5, instrument, 4, 1, 1));
        loop.addTone(new Tone(Note.D_5, instrument, 4, 2, 1));
        loop.addTone(new Tone(Note.C_5, instrument, 4, 3, 2));

        return loop;
    }
}
