package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
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

    List<BeatAndMeasure> numberOfMeasuresList;
    List<String> mListForSpinner = new ArrayList<>();
    private static int mNumberOfMeasures = 5, mNumberOfBeats = 4, mZoomLevel = 1;
    GridLayout gd;
    LinearLayout mLnNotes;
    TextView tv, tvSpinnerLegendSE;
    private Spinner mSpLoops;
    LinearLayout mLnEdit, mLnPP;
    ImageView mImPlay, mPlayPause;
    Button mBtnCreateNewLoop;
    Loop mLoop;
    TextView mTvProjectName, mTvNoOfLoopsMeasure, mTvBeatsPerMeasure, mTvEmpty;
    String oldProjectName, newProjectName;
    boolean play = true;

    private Song mSong = null;

    // These methods are called in response to a user action
    void onLoopPlacement(Loop loop, int startMeasure, int startBeat) {

    }

    void onPlayRequest() {
        mPlayPause.setImageResource(R.drawable.ic_pause_black_24px);
        play = false;
    }

    void onPauseRequest() {
        mPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24px);
        play = true;

    }

    void onEditLoopRequest(Loop loop) {
        Intent loopEditorIntent = new Intent(this, LoopEditorActivity.class);
        startActivity(loopEditorIntent);

    }

    void onCreateLoopRequest() {
        Intent intent = new Intent(SongEditorActivity.this, LoopEditorActivity.class);
        startActivity(intent);
    }

    void onEditSongNameRequest() {
        LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
        final View newFileView = layoutInflater.inflate(R.layout.activity_new_filename, null);
        AlertDialog.Builder newFileDialog = new AlertDialog.Builder(SongEditorActivity.this);

        newFileDialog.setTitle("Project Name: ");
        newFileDialog.setView(newFileView);

        final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

        newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                newProjectName = edFileName.getText().toString();
                mTvProjectName.setText("Project Name: " + newProjectName);
            }
        });

        newFileDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        newFileDialog.show();
    }

    void onExportSongRequest() {
    }

    void onTempoChange(int newTempo) {
    }

    void onVolumeChange(int newVolume) {
    }

    // These methods control the activity
    void setSong(Song song) {
        mSong = song;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_editor);

        mLnNotes = (LinearLayout) findViewById(R.id.ln_lytSE);
        mSpLoops = (Spinner) findViewById(R.id.sp_instrumentsSE);
        mLnEdit = (LinearLayout) findViewById(R.id.ln_edit);
        mImPlay = (ImageView) findViewById(R.id.im_playPauseSE);
        mTvProjectName = (TextView) findViewById(R.id.tv_projectName);
        mTvNoOfLoopsMeasure = (TextView) findViewById(R.id.tv_noLoopMeasureSE);
        mTvBeatsPerMeasure = (TextView) findViewById(R.id.tv_beatsPerMeausreSE);
        mLnPP = (LinearLayout) findViewById(R.id.ln_ppSE);

        mLnEdit.setBackgroundResource(R.drawable.border_style2);
        mLnPP.setBackgroundResource(R.drawable.border_style2);
        mBtnCreateNewLoop = (Button) findViewById(R.id.btn_createNewLoop);
        tvSpinnerLegendSE = (TextView) findViewById(R.id.tv_spinner_legendSE);
        mPlayPause = (ImageView) findViewById(R.id.im_playPauseSE);
        mTvEmpty = (TextView) findViewById(R.id.tv_empty);

        mBtnCreateNewLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateLoopRequest();
            }
        });

        songEditorNotePlacer();
        updateSpinner();

        mTvProjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditSongNameRequest();
            }
        });

        mTvNoOfLoopsMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
                final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
                AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(SongEditorActivity.this);

                numLoopDialog.setTitle("Number of loop measures: ");
                numLoopDialog.setView(numberOfLoopView);

                final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

                numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String loopNumbers = edNumLoop.getText().toString();
                        mNumberOfMeasures = Integer.parseInt(loopNumbers);
                        Log.d("TAG", "sudip:" + loopNumbers);
                        onCreate(null);
                        mTvNoOfLoopsMeasure.setText("Number of loop Measures: " + loopNumbers);

                    }
                });

                numLoopDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                numLoopDialog.show();
            }
        });

        mTvBeatsPerMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(SongEditorActivity.this);
                final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
                AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(SongEditorActivity.this);

                numLoopDialog.setTitle("Beats per measure: ");
                numLoopDialog.setView(numberOfLoopView);

                final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

                numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String beatPerMeasure = edNumLoop.getText().toString();
                        mNumberOfBeats = Integer.parseInt(beatPerMeasure);
                        onCreate(null);
                        mTvBeatsPerMeasure.setText("Beats per measure: " + beatPerMeasure);
                    }
                });

                numLoopDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                numLoopDialog.show();
            }
        });

        mPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play) {
                    onPlayRequest();
                } else
                    onPauseRequest();
            }
        });

    }

    public void songEditorNotePlacer() {

        int mMeasureCount = 1;
        int mBeatCount = 1;
        int nextMeasurePrint = 0;
        boolean printMeasureNumber = true;

        numberOfMeasuresList = new ArrayList<>();
        ArrayList<String> notesArray = new ArrayList<>();

        for (Note noteString : Note.values()) {
            notesArray.add(noteString.niceString());
        }

        for (int i = 0; i < (mNumberOfMeasures * mNumberOfBeats + mNumberOfBeats); i++) {
            if (mBeatCount <= mNumberOfBeats) {
                numberOfMeasuresList.add(new BeatAndMeasure(mBeatCount, mMeasureCount));
                mBeatCount++;
            } else {
                mBeatCount = 1;
                mMeasureCount++;
            }
        }
        for (int g = 0; g < 1; g++) {
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

                if (tv.getId() < (mNumberOfMeasures * mNumberOfBeats) && i < numberOfMeasuresList.size()) {
                    if (printMeasureNumber) {
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i).getMeausre()));
                        tv.setBackgroundResource(R.drawable.measure_border);
                        tv.setTextColor(Color.RED);
                        printMeasureNumber = false;
                        nextMeasurePrint = 0;
                    } else {
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i).getBeat()));
                        tv.setBackgroundResource(R.drawable.beat_border);
                        nextMeasurePrint++;
                        if (nextMeasurePrint == (mNumberOfBeats - 1)) {
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

    public void onEditClick(View view) {
        onEditLoopRequest(mLoop);
    }

    public void tvOnclick(View view) {
        if (mLoop == null)
            return;

        int id = view.getId();

        for (int i = 0; i < (mLoop.getNumMeasures() * mLoop.getBeatsPerMeasure()); i++) {
            for (int j = 0; j < mListForSpinner.size(); j++) {
                if (mSpLoops.getSelectedItem() == Instrument.values()[j]) {
                    gd.getChildAt(id).setBackgroundColor(ColorHelper.getColor(j));
                    id++;
                }
            }
        }
    }

    public void updateSpinner() {
        if (mSong == null) {
            mTvEmpty.setVisibility(View.VISIBLE);
            return;
        } else {
            mTvEmpty.setVisibility(View.INVISIBLE);
            for (int i = 0; i < mSong.getNumLoops(); i++) {
                mListForSpinner.add(mSong.getLoop(i).toString());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListForSpinner);
        mSpLoops.setAdapter(adapter);


        for (int i = 0; i < mListForSpinner.size(); i++) {
            if (mSpLoops.getSelectedItem().equals(Instrument.values()[i])) {
                tvSpinnerLegendSE.setBackgroundColor(ColorHelper.getColor(i));
            }
        }

    }
}

