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
    int lastId;
    HashMap<Integer, Path> activePaths;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        lastId = 0;
        paintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPath.setColor(Color.BLACK);
        paintPath.setStyle(Paint.Style.STROKE);
        paintPath.setStrokeWidth(15);

    }


    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        if(lastId!=id)
        {
            lastId = id;
        }
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if (path != null) {
            path.lineTo(x, y);
        }
        if(lastId!=id)
        {
            lastId = id;
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
    public void undo() {
        activePaths.remove(lastId);
        lastId--;
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
