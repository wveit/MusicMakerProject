package com.example.androidu.musicmaker.audio.instrument;

public class TelephoneFunction implements InstrumentFunction {
    @Override
    public double f(double radians) {
        return Math.sin(radians);
    }


}
