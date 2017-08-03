package com.example.androidu.musicmaker.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Tone;

public class AudioTestActivity extends AppCompatActivity {

    PCMGenerator gen = new PCMGenerator();
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test);


        btn = (Button)findViewById(R.id.btn_play_music);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AudioTestActivity", "paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AudioTestActivity", "resumed");
        setUpAudio();
        startAudio();
    }

    private void setUpAudio(){
        gen.setBeatsPerMeasure(4);
        gen.setNumMeasures(4);
        gen.setTempo(30);
        gen.addTone(new Tone(Note.C_5, Instrument.PIANO, 1, 1, 1));
        gen.addTone(new Tone(Note.G_5, Instrument.PIANO, 1, 1, 1));
        gen.addTone(new Tone(Note.C_5, Instrument.PIANO, 1, 2, 1));
        gen.addTone(new Tone(Note.G_5, Instrument.PIANO, 1, 3, 1));
        gen.addTone(new Tone(Note.A_5, Instrument.PIANO, 1, 3, 3));
        gen.addTone(new Tone(Note.G_5, Instrument.PIANO, 1, 4, 1));
        gen.addTone(new Tone(Note.A_5, Instrument.PIANO, 2, 1, 1));
        gen.addTone(new Tone(Note.A_5, Instrument.PIANO, 2, 2, 1));
        gen.addTone(new Tone(Note.G_5, Instrument.PIANO, 2, 3, 2));
    }

    private void startAudio(){

        new Thread(){
            @Override
            public void run() {

                int minBufferSize = AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize, AudioTrack.MODE_STREAM);
                audioTrack.play();

                short[] buffer = new short[minBufferSize];
                while(true){
                    Log.d("AudioTestActivity", "filling buffer");
                    gen.fillBuffer(buffer);
                    audioTrack.write(buffer, 0, buffer.length);
                }

            }
        }.start();


    }

    private void printBuffer(short[] buffer){
        for(short s : buffer){
            Log.d("AudioTestActivity", "buffer value: " + s);
        }
    }
}
