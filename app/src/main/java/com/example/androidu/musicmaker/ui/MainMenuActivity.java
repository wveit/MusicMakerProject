package com.example.androidu.musicmaker.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidu.musicmaker.R;
import com.example.androidu.musicmaker.filesystem.SaverLoader;
import com.example.androidu.musicmaker.model.Song;

public class MainMenuActivity extends Activity {

    public void onEditSongSelected(String songName){}

    public void onDeleteSongSelected(){}

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button create_music = (Button) findViewById(R.id.create_new_song);
        Button load_music = (Button) findViewById(R.id.load_song);
        Button exit = (Button) findViewById(R.id.exit);

        create_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewSongSelected();
            }
        });
        load_music.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onLoadSongSelected();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onNewSongSelected(){
        Song song = new Song(10, 4);
        Globals.currentSong = song;
        Intent intent = new Intent(MainMenuActivity.this, SongEditorActivity.class);
        startActivity(intent);
    }


    public void onLoadSongSelected(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View newFileView = layoutInflater.inflate(R.layout.activity_new_filename, null);
        AlertDialog.Builder newFileDialog = new AlertDialog.Builder(this);

        newFileDialog.setTitle("Name of the file to Load: ");
        newFileDialog.setView(newFileView);

        final EditText edFileName = (EditText) newFileView.findViewById(R.id.ed_filename);

        newFileDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String loadSongName = edFileName.getText().toString();
                Song song = new Song(4, 4);

                if(SaverLoader.load(song, loadSongName, MainMenuActivity.this)){
                    Globals.currentSong = song;
                    Intent intent = new Intent(MainMenuActivity.this, SongEditorActivity.class);
                    startActivity(intent);
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
}
