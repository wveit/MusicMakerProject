package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.androidu.musicmaker.model.Tone;

import java.util.ArrayList;
import java.util.List;

public class LoopEditorActivity extends Activity {



    // These methods are called in response to a user action
    void onTonePlacement(Tone newTone){}
    void onPlayRequest(){}
    void onPauseRequest(){}
    void onLoopNameChange(String oldName, String newName){} // <--- figure this one out
    void onChangeInstrument(Instrument oldInstrument, Instrument newInstrument){} // <--- this may not be necessary
    void onChangeNumberOfLoopMeasures(int oldNumLoopMeasures, int newNumLoopMeasures){}
    void onNoteScroll(Note oldLowNote, Note oldHighNote, Note newLowNote, Note newHighNote){} // <--- this may not be necessary
    void onMeasureScroll(int oldBeginMeasure, int oldEndMeasure, int newBeginMeasure, int newEndMeasure){} // <--- this may not be necessary

    // These methods are used to control the activity
    void setLoop(Loop loop){}

    private TextView mTvLoopName, mTvNoOfLoopsMeasure, mTvBeatsPerMeasure, mTvRytherZoomLevel;
    private Spinner mSpInstruments;
    private ImageView mImPlayPause;
    private TextView mTvNotes, mTvInstrumentLegend;
    private boolean play = true;
    LinearLayout mLnNotes;
    GridLayout gd;
    private static int mNumberOfMeasures = 5, mNumberOfBeats = 4, mZoomLevel = 1;

    List<BeatAndMeasure> numberOfMeasuresList;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_editor);

        mTvLoopName = (TextView) findViewById(R.id.tv_loopName);
        mTvNoOfLoopsMeasure = (TextView) findViewById(R.id.tv_noLoopMeasure);
        mTvBeatsPerMeasure = (TextView) findViewById(R.id.tv_beatsPerMeausre);
        mTvRytherZoomLevel = (TextView) findViewById(R.id.tv_rytherZoomLevel);
        mSpInstruments = (Spinner) findViewById(R.id.sp_instruments);
        mTvInstrumentLegend = (TextView) findViewById(R.id.tv_spinner_legend);
        mLnNotes = (LinearLayout) findViewById(R.id.ln_lyt);

        onUserInput();

        Context context = getApplicationContext();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.instruments, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpInstruments.setAdapter(adapter);

        mTvLoopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(LoopEditorActivity.this);
                final View newFileView = layoutInflater.inflate(R.layout.activity_new_filename, null);
                AlertDialog.Builder newFileDialog = new AlertDialog.Builder(LoopEditorActivity.this);

                newFileDialog.setTitle("Name of the file: ");
                newFileDialog.setView(newFileView);

                final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

                newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        String fileName = edFileName.getText().toString();
                        mTvLoopName.setText("Loop Name: \n" + fileName);
                    }
                });

                newFileDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                newFileDialog.show();
            }
        });

        mTvNoOfLoopsMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(LoopEditorActivity.this);
                final View numberOfLoopView = layoutInflater.inflate(R.layout.activity_loop_numbers, null);
                AlertDialog.Builder numLoopDialog = new AlertDialog.Builder(LoopEditorActivity.this);

                numLoopDialog.setTitle("Number of loop measures: ");
                numLoopDialog.setView(numberOfLoopView);

                final EditText edNumLoop = (EditText) numberOfLoopView.findViewById(R.id.ed_loopNumbers);

                numLoopDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        String loopNumbers = edNumLoop.getText().toString();
                        mNumberOfMeasures = Integer.parseInt(loopNumbers);
                        mTvNoOfLoopsMeasure.setText("Number of loop \n Measures: \n" + loopNumbers);
                        onCreate(null);
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
                        mNumberOfBeats = Integer.parseInt(beatPerMeasure);
                        mTvBeatsPerMeasure.setText("Number of loop \n Measures: \n" + beatPerMeasure);
                        onCreate(null);
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

        mSpInstruments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(mSpInstruments.getSelectedItem().toString().equals("Guitar")){
                    mTvInstrumentLegend.setBackgroundColor(Color.RED);
                } else if(mSpInstruments.getSelectedItem().toString().equals("Piano")){
                    mTvInstrumentLegend.setBackgroundColor(Color.BLUE);
                } else if(mSpInstruments.getSelectedItem().toString().equals("Recorder")){
                    mTvInstrumentLegend.setBackgroundColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    public void togglePlayPause(View view){
        final ImageView playPause = (ImageView) findViewById(R.id.im_playPause);
        playPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (play) {
                    playPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24px);
                    play = false;
                } else {
                    playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24px);
                    play = true;
                }
            }
        });
    }

    public void onUserInput() {

        int mMeasureCount = 1;
        int mBeatCount = 1;
        int nextMeasurePrint = 0;
        boolean printMeasureNumber = true;

        numberOfMeasuresList = new ArrayList<>();

        String[] chord = {"C", "B", "Bb", "A", "Ab", "G", "F#", "F", "E", "Eb", "D", "C#", "C"};

        for(int i = 1; i <= (mNumberOfMeasures * mNumberOfBeats + 1); i++){
            if(i % mNumberOfBeats == 1){
                numberOfMeasuresList.add(new BeatAndMeasure(mBeatCount, mMeasureCount));
                mMeasureCount++;
                mBeatCount = 1;
            } else{
                numberOfMeasuresList.add(new BeatAndMeasure(mBeatCount, mMeasureCount));
                mBeatCount++;
            }
        }

        for(int g = 0; g < 1; g++) {
            gd = new GridLayout(this);
            gd.setColumnCount((mNumberOfMeasures * mNumberOfBeats) + 1);
            gd.setRowCount(chord.length + 1);
            mLnNotes.addView(gd);
            int rowHeadCount = 0;
            for (int i = 0; i < ((mNumberOfMeasures * mNumberOfBeats + 1) * (chord.length + 1)); i++) {
                int check = mNumberOfMeasures * mNumberOfBeats + 1;
                tv = new TextView(this);
                tv.setGravity(50);
                tv.setContentDescription("id: " + i);
                tv.setHeight(100);
                tv.setWidth(100);
                tv.setId(i);
                if (tv.getId() <= (mNumberOfMeasures * mNumberOfBeats) && tv.getId() != 0) {
                    tv.setBackgroundResource(R.drawable.measure_border);
                    if(printMeasureNumber){
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i-1).getMeausre()));
                        tv.setTextColor(Color.RED);
                        printMeasureNumber = false;
                        nextMeasurePrint = 0;
                    } else {
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i).getBeat()));
                        nextMeasurePrint++;
                        Log.d("TAG", "index" + numberOfMeasuresList.size());
                        if(nextMeasurePrint == (mNumberOfBeats - 1)){
                            printMeasureNumber = true;
                        }
                    }
                } else if (tv.getId() > (mNumberOfMeasures * mNumberOfBeats) && tv.getId() % check == 0) {
                    check = check + mNumberOfMeasures * mNumberOfBeats;
                    tv.setText(chord[rowHeadCount]);
                    rowHeadCount++;
                    tv.setBackgroundResource(R.drawable.note_border);
                    Log.d("TAG", "count: " + rowHeadCount);
                } else if(tv.getId() != 0){
                    tv.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                    tv.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                tvClick(v);
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
    }

    public void tvClick(View view){
        ColorDrawable col = (ColorDrawable) view.getBackground();
        int colorCode = col.getColor();
        if(colorCode == getColor(R.color.buttonColor)){
            if(mSpInstruments.getSelectedItem().toString().equals(getString(R.string.guitar))){
                view.setBackgroundColor(Color.RED);
            } else if(mSpInstruments.getSelectedItem().toString().equals(getString(R.string.piano))){
                view.setBackgroundColor(Color.BLUE);
            } else if (mSpInstruments.getSelectedItem().toString().equals(getString(R.string.recorder))){
                view.setBackgroundColor(Color.BLACK);
            }
        } else {
            view.setBackgroundColor(getColor(R.color.buttonColor));
        }

        for(Note note : Note.values()){
            note.toString();
            note.ordinal();
        }
        Toast.makeText(getApplicationContext(), view.getContentDescription(), Toast.LENGTH_LONG).show();
    }
}



