package com.hfnu.corgan.poker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class Player {
    Point cardAreaLeftTop;
    Point cardAreaRightBottom;
    ArrayList<Card> playerCardList;
    boolean isMe;

    public Player(int left, int top, int right, int bottom){
        cardAreaLeftTop = new Point(left, top);
        cardAreaRightBottom = new Point(right, bottom);
        playerCardList = new ArrayList<>();
        isMe = false;
    }

    public void setMyPlayer(){
        isMe = true;
        setCard();
    }

    public void drawCardArea(Canvas canvas){
        if (isMe){
            drawMyArea(canvas);
            return;
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(cardAreaLeftTop.x, cardAreaLeftTop.y,cardAreaRightBottom.x, cardAreaRightBottom.y, paint);
        Paint text = new Paint();
        text.setColor(Color.WHITE);
        text.setTextSize(60);
        canvas.drawText(playerCardList.size() + " cards", cardAreaLeftTop.x + 30, cardAreaLeftTop.y + 150, text);
        text.setTextSize(60);
        canvas.drawText("Player", cardAreaLeftTop.x + 30, cardAreaLeftTop.y + 250, text);
    }

    void setCard(){
        playerCardList.add(new Card(1, 2));
        playerCardList.add(new Card(2, 2));
        playerCardList.add(new Card(3, 2));
        playerCardList.add(new Card(4, 2));
        playerCardList.add(new Card(1, 3));
        playerCardList.add(new Card(2, 4));
        playerCardList.add(new Card(3, 5));
        playerCardList.add(new Card(4, 6));
        playerCardList.add(new Card(2, 7));
        playerCardList.add(new Card(2, 8));
        playerCardList.add(new Card(2, 10));
        playerCardList.add(new Card(2, 11));
        playerCardList.add(new Card(2, 13));
    }

    void drawMyArea(Canvas canvas){
        int i = 0;
        for (Card card : playerCardList){
            card.draw(cardAreaLeftTop.x + i * 80, cardAreaLeftTop.y, canvas);
            i++;
        }
    }
}
