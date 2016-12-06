package com.example.swatloaner.coachme;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Kirk on 12/5/2016.
 */

public class DragObject extends TextView {

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

}
