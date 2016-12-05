package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;

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

    public boolean isDrawing = false;
    boolean isErasing = false;

    ArrayList<ImageButton> minorButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.field_draw);

        myCanvas = (MyCanvas) findViewById(R.id.soccerField);
        touchHandler = new TouchHandler(this);
        minorButtons = new ArrayList<>();
        myCanvas.setOnTouchListener(touchHandler);


        pallet = (ImageButton) findViewById(R.id.pallet);
        draw = (ImageButton) findViewById(R.id.draw);
        eraser = (ImageButton) findViewById(R.id.eraser);
        clear = (ImageButton) findViewById(R.id.clear);

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
            if (eraser.getVisibility() == View.VISIBLE) {
                setMinorIconsVisibleExcept(false, draw);
            }
        }
    }

    public void updatePath(int id, float x, float y) {
        if (isDrawing) {
            myCanvas.updatePath(id, x, y);
        }
    }
    public void undo(){
        myCanvas.undo();
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
    //when we change color background make the whole image go green or white we might need to change images completely

    @Override
    public void onClick(View view) {
        if (view.getId() == pallet.getId()) { // open the other icons

            if (isDrawing) {
                if (eraser.getVisibility() == View.VISIBLE) { // if undo is visible and drawing, turn it off except pencil
                    setMinorIconsVisibleExcept(false, draw);
                } else { // if undo is invisible and drawing, turn it all visible
                    setMinorIconsVisible(true);
                }
            } else {
                setMinorIconsVisible(draw.getVisibility() == View.INVISIBLE);
            }

        } else {
            if (view.getId() == draw.getId()) { //make other icons invisible
                isDrawing = !isDrawing;
                //TableRow.LayoutParams params = (TableRow.LayoutParams) draw.getLayoutParams();
                //params.height = 100;
                //params.width = 100;
                if (isDrawing) {
                    //draw.setImageResource(R.drawable.pencil2);
                    setMinorIconsVisibleExcept(false, draw);
                } else {
                    //draw.setImageResource(R.drawable.pencil);
                    setMinorIconsVisible(true);
                }
                //draw.setLayoutParams(params);
            } else if (view.getId() == eraser.getId()) {// if we want to implement undo button we can not set it to whiteevery other time
                undo();
            } else if (view.getId() == clear.getId()) {
                PromptDelete();
            }
        }
    }

    public void PromptDelete() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt_field, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("everything",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //delete everything method
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("just Drawings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //clear method for canvas
                                myCanvas.clear();
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
    public void promptAddPlayer(float x, float y)
    {
        LayoutInflater li = LayoutInflater.from(this);

        View promptsView = li.inflate(R.layout.prompt_field, null);
        ListView listView;
        String[] team = {"Kirk", "Joe", "Puyush", "Enrique"};
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.add_player_item, team);
        final String chosenstring = "";
        listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //save the name to chosenstring
            }
        });
        //for(int i = 0; i < ListView. )
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Team",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // put the player on the field
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Oponent",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // put the player on the field
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
    public void doubleTap(float x, float y) {
        if (!isDrawing && eraser.getVisibility() == View.INVISIBLE) {
            promptAddPlayer(x, y);

        }
    }
}
