package com.example.swatloaner.coachme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RosterFragment extends Fragment {

    TextView roster;
    User user;
    UserDataBase userDataBase;

    public RosterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roster, container, false);
        roster = (TextView) view.findViewById(R.id.roster);

        //get the current instance of user database and user

        user = ((SoccerDad) getActivity()).getUser();
        userDataBase = ((SoccerDad) getActivity()).getUserDataBase();

        populateRoster();

        return view;
    }


    private void populateRoster(){
        List<String> rosterList = userDataBase.getRoster(user.getCurrentTeam());

        String team = "";

        for ( String name :  rosterList) {
            team += name + "\n";
        }

        roster.setText(team);
    }



}
