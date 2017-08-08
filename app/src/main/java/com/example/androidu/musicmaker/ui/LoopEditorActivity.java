package com.example.androidu.musicmaker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.Tone;

public class LoopEditorActivity extends Activity {

    LinearLayout parent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_editor);


    }
}



