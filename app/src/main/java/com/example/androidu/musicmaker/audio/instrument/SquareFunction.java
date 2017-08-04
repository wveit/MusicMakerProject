package com.example.androidu.musicmaker.audio.instrument;


public class SquareFunction implements InstrumentFunction{
    @Override
    public double f(double radians) {
        radians = radians - Math.floor(radians / 2 / Math.PI) * 2 * Math.PI;
        if(radians < Math.PI / 2)
            return 1;
        else if(radians < Math.PI)
            return 0;
        else if(radians < 3 * Math.PI / 2)
            return -1;
        else
            return 0;
    }
}
