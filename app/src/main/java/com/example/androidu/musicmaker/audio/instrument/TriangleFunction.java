package com.example.androidu.musicmaker.audio.instrument;

public class TriangleFunction implements InstrumentFunction{
    @Override
    public double f(double radians) {
        radians = radians - Math.floor(radians / 2 / Math.PI) * 2 * Math.PI;
        if(radians < Math.PI){
            return radians - Math.PI / 2;
        }
        else{
            return -(radians - Math.PI / 2);
        }
    }
}
