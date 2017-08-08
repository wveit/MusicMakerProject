package com.example.androidu.musicmaker.audio.instrument;

import android.util.Log;

public class TrumpetFunction extends SampledInstrumentFunction {

    private static final double[] SAMPLES = {0.1, 0.15, 0.3, -0.3, -0.9, -0.2, 0.8, 0.2, 0.4, -0.1, 0.1, 0};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
