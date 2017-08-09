package com.example.androidu.musicmaker.ui;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class RectangleDragView extends View implements View.OnTouchListener{

    private Paint mPaint;

    private int mNumRows = 5;
    private int mNumColumns = 5;

    private int mScreenWidth = 1;
    private int mScreenHeight = 1;

    private int mSquareWidth = 1;
    private int mSquareHeight = 1;
    private float mPaddingPercent = 0.1f;

    private boolean mDragRectIsVisible = false;
    private IntRect mRect = new IntRect(1, 1, 1, 1);
    private int mRectColor = Color.BLUE;
    private RectangleDragListener mListener;

    private boolean mXDragEnabled = true;
    private boolean mYDragEnabled = true;

    private ArrayList<IntRect> mRectList = new ArrayList<IntRect>();
    private ArrayList<Integer> mColorList = new ArrayList<Integer>();


    ////////////////////////////////////////////
    //
    //  Interfaces
    //
    ////////////////////////////////////////////

    public interface RectangleDragListener {
        public void onRectangleDrag(int rectX1, int rectY1, int rectX2, int rectY2);
    }


    ////////////////////////////////////////////
    //
    //  Constructors
    //
    ////////////////////////////////////////////

    public RectangleDragView(Context context){
        super(context);
        mPaint = new Paint();
        this.setOnTouchListener(this);
    }

    public RectangleDragView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        mPaint = new Paint();
        this.setOnTouchListener(this);
    }



    ////////////////////////////////////////////
    //
    //  Methods that the user can call
    //
    ////////////////////////////////////////////

    public void setNumRows(int rows){
        mNumRows = rows;
    }

    public void setNumColumns(int columns){
        mNumColumns = columns;
    }

    public void setDragRectColor(int color){
        // color is AlphaRGB with format 0xAARRGGBB
        mRectColor = color;
    }

    public void setDragListener(RectangleDragListener listener){
        mListener = listener;
    }

    public void setXDragEnabled(boolean flag){
        mXDragEnabled = flag;
    }

    public void setYDragEnabled(boolean flag){
        mYDragEnabled = flag;
    }

    public void addRect(int x1, int y1, int x2, int y2, int color){
        IntRect rect = new IntRect(x1, y1, x2, y2);
        mRectList.add(rect);
        mColorList.add(color);
    }

    public void removeRect(int i){
        mRectList.remove(i);
        mColorList.remove(i);
    }

    public void clearRects(){
        mRectList.clear();
        mColorList.clear();
    }

    public int overlappingRect(IntRect input){
        for(int i = 0; i < mRectList.size(); i++){
            IntRect rect = mRectList.get(i);
            if(rect.overlaps(input))
                return i;
        }
        return -1;
    }

    public int containingRect(int x, int y){
        for(int i = 0; i < mRectList.size(); i++){
            IntRect rect = mRectList.get(i);
            if(rect.contains(x, y))
                return i;
        }
        return -1;
    }

    ////////////////////////////////////////////
    //
    //  Drawing and Touch Handler Methods
    //
    ////////////////////////////////////////////

    @Override
    protected void onDraw(Canvas canvas) {
        mScreenWidth = this.getWidth();
        mScreenHeight = this.getHeight();

        mSquareWidth = mScreenWidth / mNumColumns;
        mSquareHeight = mScreenHeight / mNumRows;

        canvas.drawRGB(0xFFFF, 0xFFFF, 0xFFFF);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        for(int i = 0; i <= mNumColumns; i++){
            canvas.drawLine(i * mSquareWidth, 0, i * mSquareWidth, mScreenHeight, mPaint);
        }
        for(int i = 0; i <= mNumRows; i++){
            canvas.drawLine(0, i * mSquareHeight, mScreenWidth, i * mSquareHeight, mPaint);
        }

        if(mDragRectIsVisible){
            mPaint.setColor(mRectColor);
            mPaint.setStyle(Paint.Style.FILL);
            drawMyRect(mRect.x1, mRect.y1, mRect.x2, mRect.y2, canvas, mPaint);
        }

        for(int i = 0; i < mRectList.size(); i++){
            mPaint.setColor((int)mColorList.get(i));
            IntRect rect = mRectList.get(i);
            drawMyRect(rect.x1, rect.y1, rect.x2, rect.y2, canvas, mPaint);
        }

        invalidate();
    }



    public void drawMyRect(int row1, int col1, int row2, int col2, Canvas canvas, Paint paint){
        // make sure row1 <= row2 && col1 <= col2
        if(row1 > row2){
            int temp = row2;
            row2 = row1;
            row1 = temp;
        }
        if(col1 > col2){
            int temp = col2;
            col2 = col1;
            col1 = temp;
        }

        // draw the rect
        canvas.drawRect(
                row1 * mSquareWidth + mSquareWidth * mPaddingPercent,
                col1 * mSquareHeight + mSquareHeight * mPaddingPercent,
                (row2 + 1) * mSquareWidth - mSquareWidth * mPaddingPercent,
                (col2 + 1) * mSquareHeight - mSquareHeight * mPaddingPercent,
                mPaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mDragRectIsVisible = true;
            mRect.x1 = (int)event.getX() / mSquareWidth;
            mRect.y1 = (int)event.getY() / mSquareHeight;
            mRect.x2 = mRect.x1;
            mRect.y2 = mRect.y1;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            mDragRectIsVisible = false;
            if(mListener != null)
                mListener.onRectangleDrag(mRect.x1, mRect.y1, mRect.x2, mRect.y2);
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            int rectX = (int)event.getX() / mSquareWidth;
            int rectY = (int)event.getY() / mSquareHeight;

            if(mXDragEnabled)
                mRect.x2 = rectX;
            if(mYDragEnabled)
                mRect.y2 = rectY;
        }

        return true;
    }
}
