package com.example.androidu.musicmaker.ui;

/**
 * Created by pujasudip on 8/8/17.
 */

public class ColorHelper {
    public final static int[] colorArray = {
            0xffff0000, 0xff0000ff, 0xff00ff00, 0xffff0099,
            0xff006600, 0xFF336600, 0xFFff99ff, 0xFF996633,
            0xFF00ffff, 0xFFffff00, 0xffcc33ff, 0xffff6600
    };

    public static int getColor(int index){
        return colorArray[index % colorArray.length];
    }
    public static int numColors(){return colorArray.length;}
}


