package com.example.androidu.musicmaker.filesystem;

import com.example.androidu.musicmaker.audio.test.TestSongs;
import com.example.androidu.musicmaker.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class SaverLoader {

    public static final String tag = "jsonTEST";

    public static boolean save(Song song, String filename, Context context) {

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        FileOutputStream outputStream;


        try {
//            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
            json = mapper.writeValueAsString(song);
//            System.out.println(json);

            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
//            Log.d(tag, json);
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

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                json = stringBuilder.toString();

            }

            Log.d(tag, json);
            Toast.makeText(context, "Loaded!", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Log.d(tag, json);
        return true;
    }
}


/*

    Issues and Questions:
    * Need to learn a little more about files before finalizing this interface

 */
