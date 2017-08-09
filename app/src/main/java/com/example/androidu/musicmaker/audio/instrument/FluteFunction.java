package com.example.androidu.musicmaker.audio.instrument;

public class FluteFunction extends SampledInstrumentFunction {

    private static final double[] SAMPLES = {-0.78, -0.50, -0.25, -0.01, 0.18, 0.40, 0.60,
            0.83, 0.97, 0.83, 0.59, 0.42, 0.22, 0.08, -0.05, -0.19, -0.24,
            -0.11, 0.03, 0.11, 0.19, 0.36, 0.43, 0.23, 0.05, -0.09, -0.26,
            -0.42, -0.60, -0.76};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
