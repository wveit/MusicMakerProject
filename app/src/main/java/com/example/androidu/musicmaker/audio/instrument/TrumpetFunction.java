package com.example.androidu.musicmaker.audio.instrument;

public class TrumpetFunction implements InstrumentFunction {

    private static final double[] SAMPLES = {0.1, 0.15, 0.3, -0.3, -0.9, -0.2, 0.8, 0.2, 0.4, -0.1, 0.1, 0};

    @Override
    public double f(double radians) {
        radians = radians - Math.floor(radians / 2 / Math.PI) * 2 * Math.PI;

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
