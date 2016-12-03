package com.example.swatloaner.coachme;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Kirk on 12/2/2016.
 */

public class Field_Draw extends AppCompatActivity implements View.OnClickListener {

    MyCanvas myCanvas;
    TouchHandler touchHandler;
    ImageButton pallet;
    ImageButton draw;
    ImageButton eraser;
    ImageButton clear;
    ImageButton current;

    public boolean isDrawing = false;
    boolean isErasing = false;

    ArrayList<ImageButton> minorButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.field_draw);

        myCanvas = (MyCanvas) findViewById(R.id.soccerField);
        myCanvas.setOnTouchListener(touchHandler);
        touchHandler = new TouchHandler(this);
        minorButtons = new ArrayList<>();

        pallet = (ImageButton) findViewById(R.id.pallet);
        draw = (ImageButton) findViewById(R.id.draw);
        eraser = (ImageButton) findViewById(R.id.eraser);
        clear = (ImageButton) findViewById(R.id.clear);

        current = null;

        minorButtons.add(draw);
        minorButtons.add(eraser);
        minorButtons.add(clear);

        pallet.setOnClickListener(this);
        draw.setOnClickListener(this);
        eraser.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    public void addNewPath(int id, float x, float y) {
        if (isDrawing) {
            myCanvas.addPath(id, x, y);
        }
    }

    public void updatePath(int id, float x, float y) {
        if (isDrawing) {
            myCanvas.updatePath(id, x, y);
        }
    }

    public void setMinorIconsVisible(boolean vis) {
        for (int i = 0; i < minorButtons.size(); i++) {
            if (vis) {
                minorButtons.get(i).setVisibility(View.VISIBLE);
            } else {
                minorButtons.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setMinorIconsVisibleExcept(boolean vis, ImageButton other) {
        for (int i = 0; i < minorButtons.size(); i++) {
            if (!minorButtons.get(i).equals(other)) {
                if (vis) {
                    minorButtons.get(i).setVisibility(View.VISIBLE);
                } else {
                    minorButtons.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == pallet.getId()) { // open the other icons
            ImageButton other = null;
            for (int i = 0; i < minorButtons.size(); i++) {
                if (!minorButtons.get(i).equals(current)) {
                    other = minorButtons.get(i);
                }
            }
            setMinorIconsVisible(other.getVisibility() == View.INVISIBLE);
        } else {
            if (view.getId() == draw.getId()) { //make other icons invisible
                if (current != null && current.equals(draw)) {
                    //renable buttons
                    current.setBackgroundColor(Color.WHITE);
                    isDrawing = false;
                    current = null;
                    setMinorIconsVisible(true);
                } else {
                    //set button to current
                    if (current != null) {
                        current.setBackgroundColor(Color.WHITE);
                    }
                    current = draw;
                    isDrawing = true;
                    isErasing = false;
                    current.setBackgroundColor(Color.GREEN);
                    setMinorIconsVisibleExcept(false, current);
                }
            } else if (view.getId() == eraser.getId()) {
                if (current != null &&current.equals(eraser)) {
                    //renable buttons
                    isErasing = false;
                    current.setBackgroundColor(Color.WHITE);
                    current = null;
                    setMinorIconsVisible(true);
                } else {
                    //set button to current
                    if (current != null) {
                        current.setBackgroundColor(Color.WHITE);
                    }
                    current = eraser;
                    isErasing = true;
                    isDrawing = false;
                    current.setBackgroundColor(Color.GREEN);
                    setMinorIconsVisibleExcept(false, current);
                }
            } else if (view.getId() == clear.getId()) {

                /*
                //send initial prompt to make sure they want to.
                Prompt initial = new Prompt("Are you sure?")
                if (initial.Accepted) {

                    //send prompt to clear names as well
                    Prompt prompt = new Prompt("Would you like to clear names as well?")
                    if (prompt.Accepted) {
                        //clear names

                    }

                    //now clear paths

                }
                */
                if (current != null) {
                    current.setBackgroundColor(Color.WHITE);
                }
                current = null;
                isDrawing = false;
                isErasing = false;
                setMinorIconsVisible(false);
            }
        }
    }
}
