package com.example.androidu.musicmaker.audio.instrument;


public class ViolinFunction extends SampledInstrumentFunction {

    private final double[] SAMPLES = {-0.86, -0.80, -0.77, -0.60, -0.44, -0.40, -0.32, -0.28, -0.25,
    -0.16, 0.01, 0.20, 0.26, 0.26, 0.20, 0.46, 0.62, 0.70, 0.82, 0.80, 0.76, 0.78, 0.88, 0.88, 0.84, 0.55, 0.44, 0.42,
    0.46, 0.54, 0.44, 0.42, 0.23, -0.14, -0.20, -0.18, -0.22, -0.25, -0.31, -0.34, -0.38, -0.40, -0.40, -0.44,
    -0.64, -0.80, -0.81};

    @Override
    double[] getSamples() {
        return SAMPLES;
    }
}
