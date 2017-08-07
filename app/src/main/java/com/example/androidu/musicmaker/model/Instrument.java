package com.example.androidu.musicmaker.model;

import com.example.androidu.musicmaker.audio.instrument.*;

public enum Instrument {
    TELEPHONE(new TelephoneFunction()), PIANO(new SquareFunction()), TRUMPET(new TrumpetFunction()), VIOLIN(new ViolinFunction());

    private InstrumentFunction mFunction;

    Instrument(InstrumentFunction function){
        mFunction = function;
    }

    public InstrumentFunction getFunction(){
        return mFunction;
    }
}
