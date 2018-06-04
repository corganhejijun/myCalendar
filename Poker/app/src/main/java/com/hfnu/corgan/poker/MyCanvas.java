package com.hfnu.corgan.poker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class MyCanvas extends View {
    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public MyCanvas(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setShadowLayer(2, 3, 3, Color.BLACK);
        canvas.drawRect(20, 20, 120, 120, paint);
    }
}
