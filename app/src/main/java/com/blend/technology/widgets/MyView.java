package com.blend.technology.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * unknown on 2018/1/4.
 */
public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);

        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF oval = new RectF(x, y,
                getWidth() - x, getHeight() - y);
        Path path = new Path();
        path.addArc(oval, 180, 180);
        canvas.drawPath(path, mPaint);
        mPaint.reset();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(30);
        //第二个路径，改变hoffset、voffset参数值
        canvas.drawLine(10, getHeight() / 2, 10 + 3, getHeight() / 2 + 3, mPaint);
    }
}
