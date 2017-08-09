package com.example.androidu.musicmaker.ui;


public class IntRect {
    public int x1, x2, y1, y2;

    public IntRect(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean contains(int x, int y){
        return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
    }

    public boolean overlaps(IntRect other){
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);

        int minoX = Math.min(other.x1, other.x2);
        int minoY = Math.min(other.y1, other.y2);
        int maxoX = Math.max(other.x1, other.x2);
        int maxoY = Math.max(other.y1, other.y2);

        return !(maxoX < minX || minoX > maxoX || maxoY < minY || minoY > maxoY);
    }

    public void normalize(){
        if(x1 > x2){
            int temp = x2;
            x2 = x1;
            x1 = temp;
        }
        if(y1 > y2){
            int temp = y2;
            y2 = y1;
            y1 = temp;
        }
    }

    public int x1(){return x1;}
    public int x2(){return x2;}
    public int y1(){return y1;}
    public int y2(){return y2;}

    public void setX1(int x1){this.x1 = x1;}
    public void setX2(int x2){this.x2 = x2;}
    public void setY1(int y1){this.y1 = y1;}
    public void setY2(int y2){this.y2 = y2;}
}
