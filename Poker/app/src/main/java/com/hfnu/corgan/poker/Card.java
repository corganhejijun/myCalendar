package com.hfnu.corgan.poker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Card {
    int type; // 1 ♠; 2 ♥; 3 ♣; 4 ♦; 5 Joker
    int number; // 1 A; 2 3 4 5 6 7 8 9 10; 11 J; 12 Q; 13 K
    public Card(int type, int number){
        this.type = type;
        this.number = number;
    }

    String getType(){
        switch (type){
            case 1:
                return "♠";
            case 2:
                return "♥";
            case 3:
                return "♣";
            case 4:
                return "♦";
        }
        return "*";
    }

    String getNumber(){
        switch (number){
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return number + "";
    }

    public void draw(int left, int top, Canvas canvas){
        int color = Color.BLACK;
        if (type == 2 || type == 4)
            color = Color.RED;
        Paint typePaint = new Paint();
        typePaint.setColor(color);
        typePaint.setTextSize(50);
        Paint numberPaint = new Paint();
        numberPaint.setColor(color);
        numberPaint.setTextSize(50);
        canvas.drawText(getType(), left + 10, top + 80, typePaint);
        canvas.drawText(getNumber(), left + 15, top + 160, numberPaint);
        Paint line = new Paint();
        line.setColor(Color.BLACK);
        canvas.drawLine(left, top, left + 80, top, line);
        canvas.drawLine(left, top, left, top + 250, line);
        canvas.drawLine(left, top + 250, left + 80, top + 250, line);
    }
}
