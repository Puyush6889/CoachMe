package com.example.swatloaner.coachme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FieldFragment extends Fragment {

    SoccerDad soccerDad;

    public FieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        soccerDad = (SoccerDad) getArguments().getSerializable("soccerDad");



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field, container, false);
    }




}
