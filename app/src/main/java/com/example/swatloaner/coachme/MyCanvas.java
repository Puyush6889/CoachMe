package com.example.swatloaner.coachme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Kirk on 12/2/2016.
 */

public class MyCanvas extends View {

    Paint paintPath;

    HashMap<Integer, Path> activePaths;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();

        paintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPath.setColor(Color.BLACK);
        paintPath.setStyle(Paint.Style.STROKE);
        paintPath.setStrokeWidth(15);

    }


    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if (path != null) {
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        HashMap hash = activePaths;
        if (hash.containsKey(id)) {
            hash.remove(id);
        }
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (Path path : activePaths.values()) {
            canvas.drawPath(path, paintPath);
        }
        super.onDraw(canvas);
    }

}
