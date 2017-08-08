package com.example.androidu.musicmaker.audio.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.audio.LoopPlayer;
import com.example.androidu.musicmaker.audio.SongPlayer;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Song;

public class SongPlayerTestActivity extends AppCompatActivity {

    Song song;
    SongPlayer songPlayer;
    Button playButton, pauseButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player_test);

        playButton = (Button)findViewById(R.id.btn_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        pauseButton = (Button)findViewById(R.id.btn_pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        stopButton = (Button)findViewById(R.id.btn_stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        song = TestSongs.reverie();

        songPlayer = new SongPlayer();
        songPlayer.setSong(song);
    }

    private void play(){
        if(songPlayer.isStopped()){
            songPlayer.setCurrentMeasure(1);
            songPlayer.setCurrentBeat(1);
        }
        songPlayer.play();
    }

    private void pause(){
        songPlayer.pause();
    }

    private void stop(){
        songPlayer.stop();
    }
}
