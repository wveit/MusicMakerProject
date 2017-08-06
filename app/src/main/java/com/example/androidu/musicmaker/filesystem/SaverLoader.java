package com.example.androidu.musicmaker.filesystem;

import com.example.androidu.musicmaker.audio.test.TestSongs;
import com.example.androidu.musicmaker.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import android.util.Log;


public class SaverLoader {

    public static final String tag = "jsonTEST";

    public static boolean save(Song song, String filename) {

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
//            json = mapper.writeValueAsString(song);
//            System.out.println(json);
            Log.d(tag, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean load(Song song, String filename) {
        return true;
    }
}


/*

    Issues and Questions:
    * Need to learn a little more about files before finalizing this interface

 */
