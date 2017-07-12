package com.example.androidu.musicmaker.ui;

import android.support.v7.app.AppCompatActivity;

import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Song;

public class SongEditorActivity extends AppCompatActivity {

    // These methods are called in response to a user action
    void onLoopPlacement(Loop loop, int startMeasure, int startBeat){}
    void onPlayRequest(){}
    void onPauseRequest(){}
    void onEditLoopRequest(Loop loop){}
    void onCreateLoopRequest(){}
    void onEditSongNameRequest(){}
    void onExportSongRequest(){}
    void onTempoChange(int newTempo){}
    void onVolumeChange(int newVolume){}

    // These methods control the activity
    void setSong(Song song){}
}
