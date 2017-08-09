package com.example.androidu.musicmaker.audio.instrument;

public class SaxophoneFunction extends SampledInstrumentFunction {

    private static final double[] SAMPLES = {-0.75, -0.62, -0.43, -0.29, -0.16, -0.03, 0.10,
            0.27, 0.46, 0.69, 0.82, 0.73, 0.88, 0.80, 0.63, 0.44, 0.39,
            0.53, 0.39, 0.63, 0.06, -0.08, -0.20,  -0.28, -0.37, -0.43,
            -0.59, -0.73, -0.80};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
