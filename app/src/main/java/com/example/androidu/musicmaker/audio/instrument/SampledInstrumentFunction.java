package com.example.androidu.musicmaker.audio.instrument;

import android.util.Log;

public abstract class SampledInstrumentFunction implements InstrumentFunction{

    abstract double[] getSamples();

    @Override
    public double f(double radians) {
        final double[] SAMPLES = getSamples();


        double originalRadians = radians;
        radians = radians - Math.floor(radians / 2 / Math.PI) * 2 * Math.PI;
        while(radians < 0)
            radians += 2 * Math.PI;
        if(radians < 0)
            Log.d("Trumpet", "radians less than zero. original radians: " + originalRadians + " radians: " + radians);


        double sliceSize = 2 * Math.PI / (SAMPLES.length - 1);
        int numSlices = (int)Math.floor(radians / sliceSize);
        double sliceFraction = (radians - numSlices * sliceSize) / sliceSize;

        double x1 = numSlices;
        double x2 = numSlices + 1;
        double y1 = SAMPLES[numSlices];
        double y2 = SAMPLES[numSlices + 1];

        return y1 + (y2 - y1) / (x2 - x1) * sliceFraction;
    }
}
