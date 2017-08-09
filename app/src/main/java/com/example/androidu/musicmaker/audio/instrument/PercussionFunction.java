package com.example.androidu.musicmaker.audio.instrument;

public class PercussionFunction extends SampledInstrumentFunction {

    private static final double[] SAMPLES = {0.86, 0.73, 0.58, 0.38, 0.22,0.30, 0.22, 0.08, -0.06,
            -0.19, -0.27, -0.17,-0.28, -0.41, -0.58, -0.72,-0.80, -0.87, -0.95, -0.92, -0.71, -0.49, -0.27,
            -0.08, 0.13, 0.33, 0.54, 0.74, 0.87};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
