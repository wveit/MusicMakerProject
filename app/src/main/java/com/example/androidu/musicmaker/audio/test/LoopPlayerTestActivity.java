package com.example.androidu.musicmaker.audio.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.audio.LoopPlayer;
import com.example.androidu.musicmaker.model.Loop;

public class LoopPlayerTestActivity extends AppCompatActivity {

    Loop loop;
    LoopPlayer loopPlayer;
    Button playButton, pauseButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_player_test);

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

        loop = TestLoops.samsLoop();

        loopPlayer = new LoopPlayer();
        loopPlayer.setLoop(loop);
        loopPlayer.setTempo(180);
        loopPlayer.setRepeat(true);

    }

    private void play(){
        if(loopPlayer.isStopped()) {
            loopPlayer.setCurrentMeasure(1);
            loopPlayer.setCurrentBeat(1);
        }
        loopPlayer.play();
    }

    private void pause(){
        loopPlayer.pause();
    }

    private void stop(){
        loopPlayer.stop();
    }


}
