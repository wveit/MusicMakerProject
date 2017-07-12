package com.example.androidu.musicmaker.filesystem;

import com.example.androidu.musicmaker.model.Song;

public class Exporter {
    public static boolean exportToWav(Song song, String filename){ return true; }
    public static boolean exportToOgg(Song song, String filename){ return true; }
    public static boolean exportToMidi(Song song, String filename){ return true; }

    // Note: xiph is a library for converting wav to ogg
}