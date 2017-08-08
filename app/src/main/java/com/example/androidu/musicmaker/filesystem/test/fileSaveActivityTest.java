package com.example.androidu.musicmaker.filesystem.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.androidu.musicmaker.audio.test.TestSongs;
import com.example.androidu.musicmaker.filesystem.SaverLoader;
import com.example.androidu.musicmaker.model.Song;

/**
 * Created by christopherfong on 8/4/17.
 */

public class fileSaveActivityTest extends AppCompatActivity {
    public Song lol = TestSongs.twinkleHarmony();
    public Song lol2 = new Song(10, 4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaverLoader.save(lol,"lol.txt", this);
        SaverLoader.load(lol2,"lol.txt", this);
    }


}
