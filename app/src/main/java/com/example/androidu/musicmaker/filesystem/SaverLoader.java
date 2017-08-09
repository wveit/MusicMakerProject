package com.example.androidu.musicmaker.filesystem;

import com.example.androidu.musicmaker.audio.test.TestSongs;
import com.example.androidu.musicmaker.model.Instrument;
import com.example.androidu.musicmaker.model.Loop;
import com.example.androidu.musicmaker.model.Note;
import com.example.androidu.musicmaker.model.PlacedLoop;
import com.example.androidu.musicmaker.model.Song;
import com.example.androidu.musicmaker.model.Tone;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SaverLoader {

    public static final String tag = "jsonTEST";

    public static boolean save(Song song, String filename, Context context) {

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        FileOutputStream outputStream;

        try {
//            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
            json = mapper.writeValueAsString(song);

            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
            Log.d(tag, json); // test to see if save is mapped correctly
//            System.out.println(json);
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean load(Song song, String filename, Context context) {
        String json = "";

        try {
            InputStream inputStream = context.openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                json = stringBuilder.toString();
            }

            JSONObject jsonSong = new JSONObject(json);
            int numMeasures = jsonSong.getInt("numMeasures");
            int beatsPerMeasure = jsonSong.getInt("beatsPerMeasure");
            //song = new Song(numMeasures, beatsPerMeasure);
            song.setNumMeasures(numMeasures);
            song.setBeatsPerMeasure(beatsPerMeasure);
            song.setTempo(jsonSong.getInt("tempo"));

            JSONArray jsonLoopList = jsonSong.getJSONArray("mLoopList");
            for (int i = 0; i < jsonLoopList.length(); i++) {
                JSONObject loop = jsonLoopList.getJSONObject(i);
                int loopNumMeasures = loop.getInt("numMeasures");
                int loopBPM = loop.getInt("beatsPerMeasure");
                int tempo = loop.getInt("tempo");
                String name = loop.getString("name");
                Loop tempLoop = new Loop(loopNumMeasures, loopBPM);
                tempLoop.setTempo(tempo);
                tempLoop.setName(name);
                JSONArray tones = loop.getJSONArray("mToneList");
                for (int j = 0; j < tones.length(); j++) {
                    JSONObject tone = tones.getJSONObject(j);
                    Note note = Note.valueOf(tone.getString("note"));
                    Instrument instrument = Instrument.valueOf(tone.getString("instrument"));
                    int measure = tone.getInt("startMeasure");
                    int beat = tone.getInt("startBeat");
                    int beatLength = tone.getInt("lengthInBeats");
                    Tone t = new Tone(note, instrument, measure, beat, beatLength);
                    tempLoop.addTone(t);
                }
                song.addLoop(tempLoop);
            }

            JSONArray jsonPlacedLoopList = jsonSong.getJSONArray("mPlacedLoopList");
            for (int i = 0; i < jsonPlacedLoopList.length(); i++) {
                JSONObject ploop = jsonPlacedLoopList.getJSONObject(i);
                int id = ploop.getInt("id");
                int startM = ploop.getInt("startMeasure");
                int startB = ploop.getInt("startBeat");
                int rowNum = ploop.getInt("rowNumber");
                Loop loop = song.getLoop(id);
                PlacedLoop tempPLoop = new PlacedLoop(loop, startM, startB, rowNum);
                song.addPlacedLoop(tempPLoop);
            }

            Toast.makeText(context, "Loaded!", Toast.LENGTH_LONG).show();
            Log.d(tag, json);
//            System.out.println(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}


/*

    Issues and Questions:
    * Need to learn a little more about files before finalizing this interface

 */
