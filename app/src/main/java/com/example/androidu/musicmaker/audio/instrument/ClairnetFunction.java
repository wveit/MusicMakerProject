package com.example.androidu.musicmaker.audio.instrument;

public class ClairnetFunction extends SampledInstrumentFunction {

    private static final double[] SAMPLES = {-0.82, -0.65, -0.43, -0.33, -0.30, -0.32, -0.12,
            -0.03, 0.15, 0.35, 0.54, 0.73, 0.85, 0.64, 0.51, 0.43, 0.26,
            0.22, 0.14, -0.05, -0.19, -0.39, -0.61, -0.71, -0.80};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
