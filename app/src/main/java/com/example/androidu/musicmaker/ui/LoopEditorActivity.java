package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.audio.LoopPlayer;
import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Tone;

import java.util.ArrayList;

public class LoopEditorActivity extends Activity {

    Loop mLoop;
    LoopPlayer player = new LoopPlayer();
    TextView mIntrumentColorTextView;
    Spinner mInstrumentSpinner;
    ImageView mPlayPauseImage;
    TextView mLoopNameTextView, mNumMeasuresTextView, mBeatsPerMeasureTextView;
    boolean mPlayPauseImageIsPlay = true;
    Instrument mCurrentlySelectedInstrument = Instrument.PIANO;
    RectangleDragView mRectDragView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_editor);

        mInstrumentSpinner = (Spinner) findViewById(R.id.sp_instrumentsSE);
        mLoopNameTextView = (TextView) findViewById(R.id.tv_projectName);
        mNumMeasuresTextView = (TextView) findViewById(R.id.tv_noLoopMeasureSE);
        mBeatsPerMeasureTextView = (TextView) findViewById(R.id.tv_beatsPerMeausreSE);
        mIntrumentColorTextView = (TextView) findViewById(R.id.tv_spinner_legendSE);
        mPlayPauseImage = (ImageView) findViewById(R.id.im_playPauseSE);
        mRectDragView = (RectangleDragView) findViewById(R.id.rect_drag_view);


        mRectDragView.setNumRows(Note.values().length);
        mRectDragView.setNumColumns(20);
        mRectDragView.setYDragEnabled(false);

        mLoop = new Loop(2, 4);
        mLoop.setName("new_loop");

        if (Globals.currentLoop != null)
            mLoop = Globals.currentLoop;


        mLoopNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditLoopNameRequest();
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


        mRectDragView.setDragListener(new RectangleDragView.RectangleDragListener(){
            @Override
            public void onRectangleDrag(int x1, int y1, int x2, int y2){
                LoopEditorActivity.this.rectangleDrag(x1, y1, x2, y2);
            }
        });

        mInstrumentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String)parent.getItemAtPosition(position);

                mCurrentlySelectedInstrument = Instrument.valueOf(selection);
                int color = ColorHelper.getColor(mCurrentlySelectedInstrument.ordinal());
                mIntrumentColorTextView.setBackgroundColor(color);
                mRectDragView.setDragRectColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updateActivityFromLoop();
        player.setLoop(mLoop);
    }

    void updateActivityFromLoop(){
        Log.d("LoopEditorActivity", "updateActivityFromSong()");
        mRectDragView.clearRects();
        for(int i = 0; i < mLoop.getNumTones(); i++){
            Tone tone = mLoop.getTone(i);

            Note note = tone.getNote();
            int row = (Note.values().length - 1) - note.ordinal();

            Instrument instrument = tone.getInstrument();
            int color = ColorHelper.getColor(instrument.ordinal());

            int measure = tone.getStartMeasure();
            int beat = tone.getStartBeat();
            int beatCode = (measure - 1) * mLoop.getBeatsPerMeasure() + beat - 1;

            int length = tone.getLengthInBeats();
            Log.d("LoopEditorActivity", "updateActivityFromSong() -> " + beatCode + "  " + row + "  " + (beatCode+length-1) + "  " + row + "  "  + color);
            mRectDragView.addRect(beatCode, row, beatCode + length - 1, row, color);
        }

        mLoopNameTextView.setText("Loop Name: " + mLoop.getName());
        mBeatsPerMeasureTextView.setText("Beats per measure: " + mLoop.getBeatsPerMeasure());
        mNumMeasuresTextView.setText("Num Measures: " + mLoop.getNumMeasures());

        updateSpinner();
    }


    void onTonePlacement(Tone tone){
        mLoop.addTone(tone);
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

        /* add code to pause loop */
        player.stop();
    }

    void onEditLoopNameRequest(){
        LayoutInflater layoutInflater = LayoutInflater.from(LoopEditorActivity.this);
        final View newFileView = layoutInflater.inflate(R.layout.activity_new_filename, null);
        AlertDialog.Builder newFileDialog = new AlertDialog.Builder(LoopEditorActivity.this);

        newFileDialog.setTitle("Name of the file: ");
        newFileDialog.setView(newFileView);

        final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

        newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String newSongName = edFileName.getText().toString();
                if(mLoop == null){
                    mLoopNameTextView.setText("Loop Name: \n  -no loop set-");
                }
                else{
                    mLoopNameTextView.setText("Loop Name: \n" + newSongName);
                    mLoop.setName(newSongName);
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
        LayoutInflater layoutInflater = LayoutInflater.from(LoopEditorActivity.this);
        final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
        AlertDialog.Builder numMeasuresDialog = new AlertDialog.Builder(LoopEditorActivity.this);
        numMeasuresDialog.setTitle("Number of measures: ");
        numMeasuresDialog.setView(numberOfLoopView);
        final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

        // Set up dialog callbacks
        numMeasuresDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String numMeasuresStr = edNumLoop.getText().toString();
                int numMeasures = Integer.parseInt(numMeasuresStr);
                mLoop.setNumMeasures(numMeasures);
                Log.d("TAG", "sudip:" + numMeasures);
                onCreate(null);
                mNumMeasuresTextView.setText("Number of loop \n Measures: " + numMeasures);

            }
        });

        numMeasuresDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        // Show the dialog
        numMeasuresDialog.show();
    }

    void onEditBeatsPerMeasure(){
        LayoutInflater layoutInflater = LayoutInflater.from(LoopEditorActivity.this);
        final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
        AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(LoopEditorActivity.this);

        numLoopDialog.setTitle("Beats per measure: ");
        numLoopDialog.setView(numberOfLoopView);

        final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

        numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String beatPerMeasure = edNumLoop.getText().toString();
                int bpm = Integer.parseInt(beatPerMeasure);
                mLoop.setBeatsPerMeasure(bpm);
                onCreate(null);
                mBeatsPerMeasureTextView.setText("BeatsPerMeasure: " + beatPerMeasure);
            }
        });

        numLoopDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        numLoopDialog.show();
    }


    void onTempoChange(int newTempo){}

    void onVolumeChange(int newVolume){}






    public void rectangleDrag(int x1, int y1, int x2, int y2){

        Note note = Note.values()[Note.values().length - 1 - y1];
        int noteCode1 = Math.min(x1, x2);
        int noteCode2 = Math.max(x1, x2);
        int measure = noteCode1 / mLoop.getBeatsPerMeasure() + 1;
        int beat = noteCode1 % mLoop.getBeatsPerMeasure() + 1;
        int toneLength = noteCode2 - noteCode1 + 1;

        int id = mLoop.findToneAt(note, measure, beat);
        Log.d("LoopEditorActivity", "id to remove: " + id);
        if(id >= 0){
            mLoop.removeTone(id);
            updateActivityFromLoop();
            return;
        }

        Tone tone = new Tone(note, mCurrentlySelectedInstrument, measure, beat, toneLength);
        onTonePlacement(tone);
        int color = ColorHelper.getColor(mCurrentlySelectedInstrument.ordinal());
        mRectDragView.addRect(x1, y1, x2, y2, color);
    }

    private void updateSpinner(){

        // Populate the mInstrumentSpinner with the names of loops
        ArrayList<String> instrumentNameList = makeInstrumentNameArrayList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, instrumentNameList);
        mInstrumentSpinner.setAdapter(adapter);

        // update mInstrumentColorTextView with the color of the currently selected loop
        mCurrentlySelectedInstrument = Instrument.valueOf((String) mInstrumentSpinner.getSelectedItem());
        mIntrumentColorTextView.setBackgroundColor(ColorHelper.getColor(mCurrentlySelectedInstrument.ordinal()));
    }


    private ArrayList<String> makeInstrumentNameArrayList(){
        ArrayList<String> list = new ArrayList<String>();
        for(Instrument i : Instrument.values())
            list.add(i.toString());
        return list;
    }
}
