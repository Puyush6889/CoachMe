package com.example.swatloaner.coachme;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kirk on 12/5/2016.
 */

public class DragObject extends TextView implements View.OnDragListener, View.OnTouchListener {

    public int color = 0; // red = 0, blue = 1

    public void setColor(int c) {
        this.color = c;
        if (c == 1) { // blue
            this.setBackgroundResource(R.drawable.circle_blue);
        } else {
            // default to red
            this.setBackgroundResource(R.drawable.circle);
        }
    }

    public void changeText() {
        this.changeText("");
    }

    public void changeText(String text) {
        this.setText(text);
    }

    public DragObject(Context context) {
        super(context);
        this.setGravity(Gravity.CENTER);
        this.setTextColor(Color.BLACK);
        this.changeText();
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch(dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED: // when first picked up
                break;
            case DragEvent.ACTION_DRAG_ENTERED: // get coordinates
                break;
            case DragEvent.ACTION_DRAG_EXITED: // the last coordinates before dropping
                break;
            case DragEvent.ACTION_DRAG_LOCATION: //
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            case DragEvent.ACTION_DROP:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(this);
            this.startDrag(data, shadowBuilder, this, 0);
        }
        return true;
    }
}
