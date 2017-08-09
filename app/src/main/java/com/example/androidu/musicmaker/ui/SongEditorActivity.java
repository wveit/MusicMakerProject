package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.audio.SongPlayer;
import com.example.androidu.musicmaker.audio.test.TestSongs;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.PlacedLoop;
import com.example.androidu.musicmaker.model.Song;

import java.util.ArrayList;

public class SongEditorActivity extends Activity {

    private static final String EMPTY_SPINNER_STRING = "<no loops>";
    private SongPlayer player = new SongPlayer();

    void onLoopPlacement(Loop loop, int startMeasure, int startBeat, int row){
        PlacedLoop ploop = new PlacedLoop(loop, startMeasure, startBeat, row);
        mSong.addPlacedLoop(ploop);
    }

    void onPlayRequest(){
        mPlayPauseImage.setImageResource(R.drawable.ic_pause_black_24px);
        mPlayPauseImageIsPlay = false;

        /* add code to mPlayPauseImageIsPlay song */
        player.setCurrentMeasure(1);
        player.setCurrentBeat(1);
        player.play();


    }
    void onPauseRequest(){
        mPlayPauseImage.setImageResource(R.drawable.ic_play_arrow_black_24px);
        mPlayPauseImageIsPlay = true;

        /* add code to pause song */
        player.stop();
    }

    void onEditLoopRequest(){
        if(mCurrentlySelectedLoopId < 0)
            return;

        Loop loop = mSong.getLoop(mCurrentlySelectedLoopId);
        Intent loopEditorIntent = new Intent(this, LoopEditorActivity.class);
        Globals.currentLoop = loop;
        startActivity(loopEditorIntent);
    }

    void onCreateLoopRequest(){
        Intent intent = new Intent(SongEditorActivity.this, LoopEditorActivity.class);
        Loop loop = new Loop(5, mSong.getBeatsPerMeasure());
        mSong.addLoop(loop);
        Globals.currentLoop = loop;
        startActivity(intent);
    }

    void onEditSongNameRequest(){
        LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
        final View newFileView = layoutInflater.inflate(R.layout.activity_new_filename, null);
        AlertDialog.Builder newFileDialog = new AlertDialog.Builder(SongEditorActivity.this);

        newFileDialog.setTitle("Name of the file: ");
        newFileDialog.setView(newFileView);

        final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

        newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String newSongName = edFileName.getText().toString();
                if(mSong == null){
                    mSongNameTextView.setText("Loop Name: \n  -no loop set-");
                }
                else{
                    mSongNameTextView.setText("Loop Name: \n" + newSongName);
                    mSong.setName(newSongName);
                }

            }
        });

        newFileDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        newFileDialog.show();
    }

    void onEditNumMeasures(){
        // Set up dialog layout
        LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
        final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
        AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(SongEditorActivity.this);
        numLoopDialog.setTitle("Number of measures: ");
        numLoopDialog.setView(numberOfLoopView);
        final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

        // Set up dialog callbacks
        numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String loopNumbers = edNumLoop.getText().toString();
                int numMeasures = Integer.parseInt(loopNumbers);
                mSong.setNumMeasures(numMeasures);
                Log.d("TAG", "sudip:" + loopNumbers);
                onCreate(null);
                mNumMeasuresTextView.setText("Number of loop \n Measures: " + loopNumbers);

            }
        });

        numLoopDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        // Show the dialog
        numLoopDialog.show();
    }

    void onEditBeatsPerMeasure(){
        LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
        final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
        AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(SongEditorActivity.this);

        numLoopDialog.setTitle("Beats per measure: ");
        numLoopDialog.setView(numberOfLoopView);

        final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

        numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String beatPerMeasure = edNumLoop.getText().toString();
                int bpm = Integer.parseInt(beatPerMeasure);
                mSong.setBeatsPerMeasure(bpm);
                onCreate(null);
                mBeatsPerMeasureTextView.setText("Number of loop Measures: " + beatPerMeasure);
            }
        });

        numLoopDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        numLoopDialog.show();
    }

    void onExportSongRequest(){}

    void onTempoChange(int newTempo){}

    void onVolumeChange(int newVolume){}



    private Song mSong;
    TextView mLoopColorTextView;
    private Spinner mLoopSpinner;
    ImageView mPlayPauseImage;
    Button mNewLoopButton;
    ImageView mEditLoopImage;
    TextView mSongNameTextView, mNumMeasuresTextView, mBeatsPerMeasureTextView;
    boolean mPlayPauseImageIsPlay = true;
    int mCurrentlySelectedLoopId = -1;
    RectangleDragView mRectDragView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_editor);

        mLoopSpinner = (Spinner) findViewById(R.id.sp_instrumentsSE);
        mSongNameTextView = (TextView) findViewById(R.id.tv_projectName);
        mNumMeasuresTextView = (TextView) findViewById(R.id.tv_noLoopMeasureSE);
        mBeatsPerMeasureTextView = (TextView) findViewById(R.id.tv_beatsPerMeausreSE);
        mNewLoopButton = (Button) findViewById(R.id.btn_createNewLoop);
        mEditLoopImage = (ImageView) findViewById(R.id.im_edit_loop);
        mLoopColorTextView = (TextView) findViewById(R.id.tv_spinner_legendSE);
        mPlayPauseImage = (ImageView) findViewById(R.id.im_playPauseSE);
        mRectDragView = (RectangleDragView) findViewById(R.id.rect_drag_view);


        mRectDragView.setNumRows(20);
        mRectDragView.setNumColumns(20);
        mRectDragView.setYDragEnabled(false);

//        if(Globals.currentSong == null)
//            mSong = new Song(10, 4);
//        else
//            mSong = Globals.currentSong;

        mSong = TestSongs.reverie();

        updateSpinner();


        mNewLoopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateLoopRequest();
            }
        });

        mSongNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditSongNameRequest();
            }
        });

        mNumMeasuresTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditNumMeasures();
            }
        });

        mBeatsPerMeasureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditBeatsPerMeasure();
            }
        });

        mPlayPauseImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mPlayPauseImageIsPlay) {
                    onPlayRequest();
                } else
                    onPauseRequest();
            }
        });

        mEditLoopImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onEditLoopRequest();
            }
        });

        mRectDragView.setDragListener(new RectangleDragView.RectangleDragListener(){
            @Override
            public void onRectangleDrag(int x1, int y1, int x2, int y2){
                SongEditorActivity.this.rectangleDrag(x1, y1, x2, y2);
            }
        });

        mLoopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String)parent.getItemAtPosition(position);
                if(selection.equals(EMPTY_SPINNER_STRING))
                    return;

                mCurrentlySelectedLoopId = loopIdFromName(selection);
                int color = ColorHelper.getColor(mCurrentlySelectedLoopId);
                mLoopColorTextView.setBackgroundColor(color);
                mRectDragView.setDragRectColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player.setSong(mSong);
    }



    public void rectangleDrag(int x1, int y1, int x2, int y2){
        if(mCurrentlySelectedLoopId < 0)
            return;


        Loop loop = mSong.getLoop(mCurrentlySelectedLoopId);
        int row = y1;
        int noteCode = Math.min(x1, x2);
        int measure = noteCode / mSong.getBeatsPerMeasure() + 1;
        int beat = noteCode % mSong.getBeatsPerMeasure() + 1;
        int loopLength = loop.getNumMeasures() * loop.getBeatsPerMeasure();

        onLoopPlacement(loop, measure, beat, row);
        int color = ColorHelper.getColor(mCurrentlySelectedLoopId);
        mRectDragView.addRect(x1, y1, x1 + loopLength, y2, color);
    }

    private void updateSpinner(){

        // Populate the mLoopSpinner with the names of loops
        ArrayList<String> loopNameList = makeLoopNameArrayList();
        if(loopNameList.isEmpty())
            loopNameList.add(EMPTY_SPINNER_STRING);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loopNameList);
        mLoopSpinner.setAdapter(adapter);

        // update mLoopColorTextView with the color of the currently selected loop
        int currentSelectedLoopId = loopIdFromName((String)mLoopSpinner.getSelectedItem());
        if(currentSelectedLoopId >= 0)
            mLoopColorTextView.setBackgroundColor(ColorHelper.getColor(currentSelectedLoopId));
    }

    private int loopIdFromName(String name){
        for(int i = 0; i < mSong.getNumLoops(); i++){
            Loop loop = mSong.getLoop(i);
            if(name.equals(loop.getName()))
                return loop.getId();
        }
        return -1;
    }

    private ArrayList<String> makeLoopNameArrayList(){
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < mSong.getNumLoops(); i++)
            list.add(mSong.getLoop(i).getName());
        return list;
    }
}
