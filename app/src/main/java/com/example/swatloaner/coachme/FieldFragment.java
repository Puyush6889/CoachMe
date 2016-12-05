package com.example.swatloaner.coachme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FieldFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public boolean isDrawing = false;
    MyCanvas myCanvas;
    TouchHandler touchHandler;
    ImageButton pallet;
    ImageButton draw;
    ImageButton eraser;
    ImageButton clear;
    boolean isErasing = false;
    ArrayList<ImageButton> minorButtons;


    public FieldFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field_friagment, container, false);
        myCanvas = (MyCanvas) view.findViewById(R.id.soccerField);
        touchHandler = new TouchHandler(this);//need to be fixed
        minorButtons = new ArrayList<>();
        myCanvas.setOnTouchListener(touchHandler);


        pallet = (ImageButton) view.findViewById(R.id.pallet);
        draw = (ImageButton) view.findViewById(R.id.draw);
        eraser = (ImageButton) view.findViewById(R.id.eraser);
        clear = (ImageButton) view.findViewById(R.id.clear);

        minorButtons.add(draw);
        minorButtons.add(eraser);
        minorButtons.add(clear);

        pallet.setOnClickListener(this);
        draw.setOnClickListener(this);
        eraser.setOnClickListener(this);
        clear.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    public void addNewPath(int num, float x, float y) {
    }

    public void updatePath(int num, float x, float y) {
    }

    public void doubleTap(float x, float y) {
    }
}

