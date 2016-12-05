package com.example.swatloaner.coachme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        Intent intent = getActivity().getIntent();

        userDataBase = (UserDataBase) intent.getExtras().get("database");

        String eml = (String) intent.getExtras().get("user_email");

        user = userDataBase.getUsers().get(eml);

        populateRoster(user.getTeams().get(1));

        return view;
    }

    private void populateRoster(String team){
        String rosterStr = userDataBase.getRoster(team);

        roster.setText(rosterStr);
    }
}
