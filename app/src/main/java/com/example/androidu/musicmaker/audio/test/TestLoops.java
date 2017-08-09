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

    public static Loop samsLoop(){
        Loop loop = new Loop(4, 4);

        Instrument instrument = Instrument.PERCUSSION;
        loop.addTone(new Tone(Note.C_5, instrument, 1, 1, 1));
        loop.addTone(new Tone(Note.D_5, instrument, 1, 2, 1));
        loop.addTone(new Tone(Note.E_5, instrument, 1, 3, 1));
        loop.addTone(new Tone(Note.F_5, instrument, 1, 4, 1));
        loop.addTone(new Tone(Note.G_5, instrument, 2, 1, 1));
        loop.addTone(new Tone(Note.A_5, instrument, 2, 2, 1));
        loop.addTone(new Tone(Note.B_5, instrument, 2, 3, 1));
        loop.addTone(new Tone(Note.C_6, instrument, 2, 4, 1));

        return loop;
    }

    public static Loop reverieLoop1(){
        Loop loop = new Loop(4, 4);

        Instrument instrument = Instrument.PIANO;
        loop.addTone(new Tone(Note.B_FLAT_3, instrument, 1, 1, 1));
        loop.addTone(new Tone(Note.C_4, instrument, 1, 2, 1));
        loop.addTone(new Tone(Note.D_4, instrument, 1, 3, 1));
        loop.addTone(new Tone(Note.G_4, instrument, 1, 4, 2));
        loop.addTone(new Tone(Note.D_4, instrument, 2, 2, 1));
        loop.addTone(new Tone(Note.C_4, instrument, 2, 3, 1));
        loop.addTone(new Tone(Note.B_FLAT_3, instrument, 2, 4, 2));
        loop.addTone(new Tone(Note.C_4, instrument, 3, 2, 1));
        loop.addTone(new Tone(Note.D_4, instrument, 3, 3, 1));
        loop.addTone(new Tone(Note.G_4, instrument, 3, 4, 2));
        loop.addTone(new Tone(Note.D_4, instrument, 4, 2, 1));
        loop.addTone(new Tone(Note.C_4, instrument, 4, 3, 1));
        loop.addTone(new Tone(Note.B_FLAT_3, instrument, 4, 4, 1));

        return loop;
    }

    public static Loop reverieLoop2(){
        Loop loop = new Loop(6, 4);

        Instrument instrument = Instrument.PIANO;
        loop.addTone(new Tone(Note.A_3, instrument, 1, 1, 1));
        loop.addTone(new Tone(Note.B_FLAT_3, instrument, 1, 2, 1));
        loop.addTone(new Tone(Note.C_4, instrument, 1, 3, 1));
        loop.addTone(new Tone(Note.F_4, instrument, 1, 4, 2));
        loop.addTone(new Tone(Note.C_4, instrument, 2, 2, 1));
        loop.addTone(new Tone(Note.B_FLAT_3, instrument, 2, 3, 1));
        loop.addTone(new Tone(Note.A_3, instrument, 2, 4, 1));

        loop.addTone(new Tone(Note.E_3, instrument, 3, 1, 1));
        loop.addTone(new Tone(Note.F_3, instrument, 3, 2, 1));
        loop.addTone(new Tone(Note.G_3, instrument, 3, 3, 1));
        loop.addTone(new Tone(Note.C_4, instrument, 3, 4, 2));
        loop.addTone(new Tone(Note.G_3, instrument, 4, 2, 1));
        loop.addTone(new Tone(Note.F_3, instrument, 4, 3, 1));
        loop.addTone(new Tone(Note.E_3, instrument, 4, 4, 1));


        loop.addTone(new Tone(Note.D_4, instrument, 5, 1, 1));
        loop.addTone(new Tone(Note.A_4, instrument, 5, 2, 7));

        return loop;
    }

    public static Loop reverieLoop3(){
        Loop loop = new Loop(16, 4);

        Instrument instrument = Instrument.VIOLIN;
        loop.addTone(new Tone(Note.G_5, instrument, 1, 1, 4));
        loop.addTone(new Tone(Note.D_5, instrument, 2, 1, 6));
        loop.addTone(new Tone(Note.E_5, instrument, 3, 3, 1));
        loop.addTone(new Tone(Note.F_5, instrument, 3, 4, 1));
        loop.addTone(new Tone(Note.G_5, instrument, 4, 1, 2));
        loop.addTone(new Tone(Note.E_5, instrument, 4, 3, 1));
        loop.addTone(new Tone(Note.D_5, instrument, 4, 4, 1));
        loop.addTone(new Tone(Note.E_5, instrument, 5, 1, 2));
        loop.addTone(new Tone(Note.C_5, instrument, 5, 3, 2));
        loop.addTone(new Tone(Note.E_5, instrument, 6, 1, 2));
        loop.addTone(new Tone(Note.D_5, instrument, 6, 3, 8));

        loop.addTone(new Tone(Note.B_FLAT_4, instrument, 8, 3, 2));
        loop.addTone(new Tone(Note.D_5, instrument, 9, 1, 2));
        loop.addTone(new Tone(Note.E_5, instrument, 9, 3, 2));
        loop.addTone(new Tone(Note.F_5, instrument, 10, 1, 2));
        loop.addTone(new Tone(Note.C_5, instrument, 10, 3, 8));
        loop.addTone(new Tone(Note.G_4, instrument, 12, 3, 4));
        loop.addTone(new Tone(Note.A_4, instrument, 13, 3, 16));

        return loop;
    }
}
