package com.example.androidu.musicmaker.filesystem;

import com.example.androidu.musicmaker.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SaverLoader {

    ObjectMapper mapper = new ObjectMapper();
    

    public static boolean save(Song song, String filename){ return true; }
    public static boolean load(Song song, String filename){ return true; }
}


/*

    Issues and Questions:
    * Need to learn a little more about files before finalizing this interface

 */
