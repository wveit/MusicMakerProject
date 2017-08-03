package com.example.androidu.musicmaker.model;

public enum Note {
    C_3(130.81f), C_SHARP_3(138.59f), D_3(146.83f), E_FLAT_3(155.56f), E_3(164.81f), F_3(174.61f),
    F_SHARP_3(185.00f), G_3(196.00f), A_FLAT_3(207.65f), A_3(220.00f), B_FLAT_3(233.08f), B_3(246.94f),
    C_4(261.63f), C_SHARP_4(277.18f), D_4(293.66f), E_FLAT_4(311.13f), E_4(329.63f), F_4(349.23f),
    F_SHARP_4(369.99f), G_4(392.00f), A_FLAT_4(415.30f), A_4(440.00f), B_FLAT_4(466.16f), B_4(493.88f),
    C_5(523.25f), C_SHARP_5(554.37f), D_5(587.33f), E_FLAT_5(622.25f), E_5(659.25f), F_5(698.46f),
    F_SHARP_5(739.99f), G_5(783.99f), A_FLAT_5(830.61f), A_5(880.00f), B_FLAT_5(932.33f), B_5(987.77f),
    C_6(1046.50f);

    private float mFreq;

    Note(float freq){
        mFreq = freq;
    }

    public float freq(){
        return mFreq;
    }

}

