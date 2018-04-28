package com.example.shijiabing.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shijiabing on 2018/4/29.
 */

public class CircleProgress extends View {
    private int progress;
    private int speed = 20;
    private boolean change;
    private int circleWidth = 40;
    private Paint mPaint;
    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        new Thread(() -> {
            while (true){
                progress++;
                if (progress == 360){
                    progress = 0;
                    change = !change;
                }
                postInvalidate();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth()/2;
        int radius = centre - circleWidth/2;
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        if (change){
            mPaint.setColor(Color.RED);
            canvas.drawCircle(centre, centre, radius, mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawArc(oval, -90, progress, false, mPaint);
        }else {
            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(centre, centre, radius, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawArc(oval, -90, progress, false, mPaint);
        }
    }
}
