package com.hfnu.corgan.poker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

public class MyCanvas extends View {
    Player player1;
    Player player2;
    Player myPlayer;

    public MyCanvas(Context context) {
        super(context);
        initPlayer(context);
    }

    public MyCanvas(Context context, AttributeSet attrs){
        super(context, attrs);
        initPlayer(context);
    }

    public MyCanvas(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initPlayer(context);
    }

    void initPlayer(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int totalWidth = dm.widthPixels;
        player1 = new Player(20, 20, 320, 320);
        player2 = new Player(totalWidth - 320, 20, totalWidth - 20, 320);
        myPlayer = new Player(20, 800, totalWidth - 20, 1100);
        myPlayer.setMyPlayer();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (player1 != null)
            player1.drawCardArea(canvas);
        if (player2 != null)
            player2.drawCardArea(canvas);
        if (myPlayer != null)
            myPlayer.drawCardArea(canvas);
    }
}
