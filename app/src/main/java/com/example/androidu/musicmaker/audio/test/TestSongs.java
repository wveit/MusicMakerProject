package com.example.androidu.musicmaker.audio.test;


import com.example.androidu.musicmaker.model.PlacedLoop;
import com.example.androidu.musicmaker.model.Song;

public class TestSongs {
    public static Song twinkleHarmony(){
        Song song = new Song(10, 4);
        song.addLoop(TestLoops.twinkleLoop());

        song.addPlacedLoop(new PlacedLoop(song.getLoop(0), 1, 1, 1));
        song.addPlacedLoop(new PlacedLoop(song.getLoop(0), 2, 1, 2));

        return song;
    }
}
