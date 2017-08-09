package com.example.androidu.musicmaker.ui;

/**
 * Created by pujasudip on 8/8/17.
 */

public class ColorHelper {
    public final static int[] colorArray = {0xffff0000, 0xff0000ff, 0xff00ff00, 0xffff0099, 0xff006600};

    public static int getColor(int index){
        return colorArray[index % colorArray.length];
    }
}

//class waka {
//    public void updateSpinner{
//
//        if(mSong == null)
//            return;
//
//        for (int i = 0; i < mSong.getNumLoops(); i++) {
//            Loop loop = mSong.getLoop(i);
//            String loopName = loop.getName();
//            int loopColor = ColorHelper.getColor(loop.id);
//            spinner.addItem(loop.getName(), loopColor);
//        }
//    }
//
//}
