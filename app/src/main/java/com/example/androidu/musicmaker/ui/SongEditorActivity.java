package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.model.BeatAndMeasure;
import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Song;
import com.example.androidu.musicmaker.model.Tone;

import java.util.ArrayList;
import java.util.List;

public class SongEditorActivity extends Activity {

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

    List<BeatAndMeasure> numberOfMeasuresList;
    List<String> mInstrumentForSpinner = new ArrayList<>();
    private static int mNumberOfMeasures = 5, mNumberOfBeats = 4, mZoomLevel = 1;
    GridLayout gd;
    LinearLayout mLnNotes;
    TextView tv;
    private Spinner mSpInstruments;
    LinearLayout mLnEdit, mLnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_editor);

        mLnNotes = (LinearLayout) findViewById(R.id.ln_lytSE);
        mSpInstruments = (Spinner) findViewById(R.id.sp_instrumentsSE);
        mLnEdit = (LinearLayout) findViewById(R.id.ln_edit);
        mLnPlay = (LinearLayout) findViewById(R.id.ln_play);

        mLnEdit.setBackgroundResource(R.drawable.border_style);
        mLnPlay.setBackgroundResource(R.drawable.border_style);

        songEditorNotePlacer();

        for(Instrument instrumentSp: Instrument.values()){
            mInstrumentForSpinner.add(instrumentSp.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mInstrumentForSpinner);
        mSpInstruments.setAdapter(adapter);

    }

    public void songEditorNotePlacer() {

        int mMeasureCount = 1;
        int mBeatCount = 1;
        int nextMeasurePrint = 0;
        boolean printMeasureNumber = true;

        numberOfMeasuresList = new ArrayList<>();
        ArrayList<String> notesArray = new ArrayList<>();

        for(Note noteString: Note.values()){
            notesArray.add(noteString.niceString());
        }

        for(int i = 0; i < (mNumberOfMeasures * mNumberOfBeats + mNumberOfBeats); i++){
            if(mBeatCount <= mNumberOfBeats){
                numberOfMeasuresList.add(new BeatAndMeasure(mBeatCount, mMeasureCount));
                mBeatCount++;
            } else{
                mBeatCount = 1;
                mMeasureCount++;
            }
        }
        for(int i = 0; i < numberOfMeasuresList.size(); i++) {
            Log.d("TAG", "listMine:" + numberOfMeasuresList.size());
        }
        for(int g = 0; g < 1; g++) {
            gd = new GridLayout(this);
            gd.setColumnCount(mNumberOfMeasures * mNumberOfBeats);
            gd.setRowCount(notesArray.size());
            mLnNotes.addView(gd);
            for (int i = 0; i < (mNumberOfMeasures * mNumberOfBeats) * notesArray.size(); i++) {
                tv = new TextView(this);
                tv.setGravity(Gravity.CENTER);
                tv.setHeight(80);
                tv.setWidth(80);
                tv.setId(i);

              if (tv.getId() < (mNumberOfMeasures * mNumberOfBeats)) {
                    if(printMeasureNumber){
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i).getMeausre()));
                        tv.setBackgroundResource(R.drawable.measure_border);
                        tv.setTextColor(Color.RED);
                        printMeasureNumber = false;
                        nextMeasurePrint = 0;
                    } else {
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i).getBeat()));
                        tv.setBackgroundResource(R.drawable.beat_border);
                        nextMeasurePrint++;
                        if(nextMeasurePrint == (mNumberOfBeats - 1)){
                            printMeasureNumber = true;
                        }
                    }
                } else {
//                    tv.setContentDescription((numberOfMeasuresList.get(beatAndMeasureIndex).getMeausre() -1)
//                            + "-" + numberOfMeasuresList.get(beatAndMeasureIndex).getBeat() + "-" + "1");
//                    beatAndMeasureIndex++;

                    tv.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                    tv.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                tvOnclick(v);
                                return true;
                            }
                            return false;
                        }
                    });
                }
                gd.setUseDefaultMargins(true);
                gd.addView(tv);
            }
        }
        //repopulateNotePlaced();
    }
    public void onEditClick(View view){
        Intent loopEditorIntent = new Intent(this, LoopEditorActivity.class);
        startActivity(loopEditorIntent);
    }

    public void tvOnclick(View view){
        int id = view.getId();
        Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < 4; i++){
            gd.getChildAt(id).setBackgroundColor(Color.RED);
            id++;
        }
    }
}
