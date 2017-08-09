package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LoopEditorActivity extends Activity {

    // These methods are called in response to a user action
    void onTonePlacement(Tone newTone){
        //this is called when tone is placed by the user
    }
    void onPlayRequest(){
        mPlayPause.setImageResource(R.drawable.ic_pause_black_24px);
        play = false;
    }
    void onPauseRequest(){
        mPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24px);
        play = true;
    }
    void onLoopNameChange(String oldName, String newName){
        Toast.makeText(getApplicationContext(), oldName + "has been changed to " + newName + ".", Toast.LENGTH_SHORT).show();
    } // <--- figure this one out
    void onChangeInstrument(Instrument oldInstrument, Instrument newInstrument){
        oldInstrument = newInstrument;
    } // <--- this may not be necessary
    void onChangeNumberOfLoopMeasures(int oldNumLoopMeasures, int newNumLoopMeasures){

    }
    void onNoteScroll(Note oldLowNote, Note oldHighNote, Note newLowNote, Note newHighNote){} // <--- this may not be necessary
    void onMeasureScroll(int oldBeginMeasure, int oldEndMeasure, int newBeginMeasure, int newEndMeasure){} // <--- this may not be necessary

    // These methods are used to control the activity
    void setLoop(Loop loop){}

    private TextView mTvLoopName, mTvNoOfLoopsMeasure, mTvBeatsPerMeasure, mTvRytherZoomLevel;
    private Spinner mSpInstruments;
    private ImageView mPlayPause;
    private TextView mTvNotes, mTvInstrumentLegend;
    private boolean play = true;
    LinearLayout mLnNotes, mLnPP;
    GridLayout gd;
    private static int mNumberOfMeasures = 5, mNumberOfBeats = 4, mZoomLevel = 1;
    Instrument mInstrument;
    Note mNote;
    String oldFileName = "New_File", newFileName;
    //piano is the default instrument for oldInstrument
    Instrument oldInstrument = Instrument.PIANO, newInstrument;

    List<BeatAndMeasure> numberOfMeasuresList;
    List<Tone> tonePlacedByUser = new ArrayList<>();
    List<String> addingViewDescription = new ArrayList<>();
    List<String> mInstrumentForSpinner;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_editor);

        mTvLoopName = (TextView) findViewById(R.id.tv_loopName);
        mTvLoopName.append(oldFileName);
        mTvNoOfLoopsMeasure = (TextView) findViewById(R.id.tv_noLoopMeasure);
        mTvBeatsPerMeasure = (TextView) findViewById(R.id.tv_beatsPerMeausre);
        mTvRytherZoomLevel = (TextView) findViewById(R.id.tv_rytherZoomLevel);
        mSpInstruments = (Spinner) findViewById(R.id.sp_instruments);
        mTvInstrumentLegend = (TextView) findViewById(R.id.tv_spinner_legend);
        mLnNotes = (LinearLayout) findViewById(R.id.ln_lyt);
        mLnPP = (LinearLayout) findViewById(R.id.ln_ppLE);
        mLnPP.setBackgroundResource(R.drawable.border_style2);
        mPlayPause = (ImageView) findViewById(R.id.im_playPauseLE);

        onUserInput();

//        Context context = getApplicationContext();
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.instruments, android.R.layout.simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpInstruments.setAdapter(adapter);

        mInstrumentForSpinner = new ArrayList<>();

        for(Instrument instrumentSp: Instrument.values()){
            mInstrumentForSpinner.add(instrumentSp.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mInstrumentForSpinner);
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
                        newFileName = edFileName.getText().toString();
                        mTvLoopName.setText("Loop Name: " + newFileName);
                        onLoopNameChange(oldFileName, newFileName);
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
                        Log.d("TAG", "sudip:" + loopNumbers);
                        onCreate(null);
                        mTvNoOfLoopsMeasure.setText("Number of loop \n Measures: " + loopNumbers);

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
                        onCreate(null);
                        mTvBeatsPerMeasure.setText("Number of loop \n Measures: " + beatPerMeasure);
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

                for(int index = 0; index < mInstrumentForSpinner.size(); index++){
                    if(mSpInstruments.getSelectedItem().toString().equals(Instrument.values()[index].toString())){
                        mTvInstrumentLegend.setBackgroundColor(ColorHelper.getColor(index));
                    }
                }
//                if(mSpInstruments.getSelectedItem().toString().equals(Instrument.TELEPHONE.toString())){
//                    mTvInstrumentLegend.setBackgroundColor(Color.RED);
//                    newInstrument = Instrument.TELEPHONE;
//                } else if(mSpInstruments.getSelectedItem().toString().equals(Instrument.PIANO.toString())){
//                    mTvInstrumentLegend.setBackgroundColor(Color.BLUE);
//                    newInstrument = Instrument.PIANO;
//                } else if(mSpInstruments.getSelectedItem().toString().equals(Instrument.TRUMPET.toString())){
//                    mTvInstrumentLegend.setBackgroundColor(Color.BLACK);
//                    newInstrument = Instrument.TRUMPET;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mPlayPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(play) {
                    onPlayRequest();
                } else
                    onPauseRequest();
            }
        });
        onChangeInstrument(oldInstrument, newInstrument);
    }




//    public void togglePlayPause(View view){
//        final ImageView playPause = (ImageView) findViewById(R.id.im_playPauseLE);
//        playPause.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (play) {
//                    playPause.setImageResource(R.drawable.ic_pause_black_24px);
//                    play = false;
//                    onPauseRequest();
//                } else {
//                    playPause.setImageResource(R.drawable.ic_play_arrow_black_24px);
//                    play = true;
//                    onPlayRequest();
//                }
//            }
//        });
//    }

    public void onUserInput() {

        int mMeasureCount = 1;
        int mBeatCount = 1;
        int nextMeasurePrint = 0;
        boolean printMeasureNumber = true;
        int noteIndex = -1, beatAndMeasureIndex = 1;

        numberOfMeasuresList = new ArrayList<>();
        ArrayList<String> notesArray = new ArrayList<>();

        for(Note noteString: Note.values()){
            notesArray.add(noteString.niceString());
        }


        //String[] notesArray = {"C", "B", "Bb", "A", "Ab", "G", "F#", "F", "E", "Eb", "D", "C#", "C"};

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
            gd.setRowCount(notesArray.size() + 1);
            mLnNotes.addView(gd);
            int rowHeadCount = 0;
            for (int i = 0; i < ((mNumberOfMeasures * mNumberOfBeats + 1) * (notesArray.size() + 1)); i++) {
                int check = mNumberOfMeasures * mNumberOfBeats + 1;
                tv = new TextView(this);
                tv.setGravity(Gravity.CENTER);
                tv.setContentDescription("id: " + i);
                tv.setHeight(80);
                tv.setWidth(80);
                tv.setId(i);

                if(tv.getId() == 0){
                    //no action to be taken
                } else if (tv.getId() <= (mNumberOfMeasures * mNumberOfBeats) && tv.getId() != 0) {
                    if(printMeasureNumber){
                        tv.setText(String.valueOf(numberOfMeasuresList.get(i-1).getMeausre()));
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
                } else if (tv.getId() > (mNumberOfMeasures * mNumberOfBeats) && tv.getId() % check == 0) {
                    check = check + mNumberOfMeasures * mNumberOfBeats;
                    tv.setText(notesArray.get(rowHeadCount));
                    rowHeadCount++;
                    tv.setBackgroundResource(R.drawable.note_border);
                    noteIndex++;
                    beatAndMeasureIndex = 1;
                } else {
                    tv.setContentDescription(notesArray.get(noteIndex) + "-" + (numberOfMeasuresList.get(beatAndMeasureIndex).getMeausre() -1)
                            + "-" + numberOfMeasuresList.get(beatAndMeasureIndex).getBeat() + "-" + "1");
                    beatAndMeasureIndex++;
                    Log.d("TAG", "indexS: " + beatAndMeasureIndex);

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
        repopulateNotePlaced();
    }

    public void tvClick(View view) {
        String instrument = "Deleting";

        ColorDrawable col = (ColorDrawable) view.getBackground();
        int colorCode = col.getColor();
        if (colorCode == getColor(R.color.buttonColor)) {
            for(int i = 0; i < Instrument.values().length; i++) {
                if (mSpInstruments.getSelectedItem().toString().equals(Instrument.values()[i].toString())) {
                    view.setBackgroundColor(ColorHelper.getColor(i));
                    instrument = Instrument.values()[i].toString();
                }
            }
//            if (mSpInstruments.getSelectedItem().toString().equals(Instrument.TELEPHONE.toString())) {
//                view.setBackgroundColor(Color.RED);
//                instrument = Instrument.TELEPHONE.toString();
//            } else if (mSpInstruments.getSelectedItem().toString().equals(Instrument.PIANO.toString())) {
//                view.setBackgroundColor(Color.BLUE);
//                instrument = Instrument.PIANO.toString();
//            } else if (mSpInstruments.getSelectedItem().toString().equals(Instrument.TRUMPET.toString())) {
//                view.setBackgroundColor(Color.BLACK);
//                instrument = Instrument.TRUMPET.toString();
//            }
        } else {
            view.setBackgroundColor(getColor(R.color.buttonColor));
        }

        String viewDescription = view.getContentDescription().toString();
        String completeToneString = instrument + "-" + viewDescription;
        addingViewDescription.add(completeToneString);
        String[] splitViewDescription = viewDescription.split("-");

        String noteFromTv = splitViewDescription[0];

        for (Instrument ins : Instrument.values()) {
            if (ins.toString().equals(instrument)) {
                mInstrument = ins;
            }
        }

        for (Note noteToAdd : Note.values()) {
            if (noteToAdd.niceString().equals(noteFromTv)) {
                mNote = noteToAdd;
            }
        }
        Toast.makeText(getApplicationContext(), completeToneString, Toast.LENGTH_SHORT).show();
        tonePlacedByUser.add(new Tone(mNote, mInstrument, Integer.parseInt(splitViewDescription[1]), Integer.parseInt(splitViewDescription[2]), 1));
        onTonePlacement(new Tone(mNote, mInstrument, Integer.parseInt(splitViewDescription[1]), Integer.parseInt(splitViewDescription[2]), 1));
    }


    public void repopulateNotePlaced(){
        if(tonePlacedByUser.size() > 0) {
            for (int i = 0; i < (gd.getColumnCount() * gd.getRowCount()); i++) {
                for (int j = 0; j < tonePlacedByUser.size(); j++) {
                    String comparingTobePlacedToLoop = tonePlacedByUser.get(j).getNote().niceString() + "-" + tonePlacedByUser.get(j).getStartMeasure()
                            + "-" + tonePlacedByUser.get(j).getStartBeat() + "-" + tonePlacedByUser.get(j).getLengthInBeats();
                    Log.d("TAG", "gd:" + comparingTobePlacedToLoop);
                    if (gd.getChildAt(i).getContentDescription().equals(comparingTobePlacedToLoop)) {
                        for(int indexInner = 0; indexInner < Instrument.values().length; indexInner++){
                            if (tonePlacedByUser.get(j).getInstrument().equals(Instrument.values()[indexInner])) {
                                gd.getChildAt(i).setBackgroundColor(ColorHelper.getColor(indexInner));
                            }
                        }
//                        if (tonePlacedByUser.get(j).getInstrument().equals(Instrument.TELEPHONE)) {
//                            gd.getChildAt(i).setBackgroundColor(Color.RED);
//                        }
//                        if (tonePlacedByUser.get(j).getInstrument().equals(Instrument.PIANO)) {
//                            gd.getChildAt(i).setBackgroundColor(Color.BLUE);
//                        }
//                        if (tonePlacedByUser.get(j).getInstrument().equals(Instrument.TRUMPET)) {
//                            gd.getChildAt(i).setBackgroundColor(Color.BLACK);
//                        }

                    }
                }
            }
        }
    }
}



