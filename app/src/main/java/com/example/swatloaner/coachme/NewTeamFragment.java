package com.example.swatloaner.coachme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTeamFragment extends Fragment implements View.OnClickListener{


    EditText searchKey;
//    Button search;
    Button add;
    ListView teamList;

    private ArrayAdapter<String> adapter;

    User user;
    UserDataBase userDataBase;
//    List<String> toAdd;
//    List<User> teamsToAdd;
    public NewTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_team, container, false);

        user = ((SoccerDad) getActivity()).getUser();
        userDataBase = ((SoccerDad) getActivity()).getUserDataBase();

        searchKey = (EditText) view.findViewById(R.id.searchKey);
//        search = (Button) view.findViewById(R.id.search);
//        search.setOnClickListener(this);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(this);

//        toAdd = new ArrayList<>();
//
//        teamsToAdd = new ArrayList<>();




        teamList = (ListView) view.findViewById(R.id.teamList);
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.test_list_item, user.getTeams());
        teamList.setAdapter(adapter);
        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        String newTeamName = "";
        if (view.getId() == add.getId()){
            newTeamName = searchKey.getText().toString();
            if (!user.getTeams().contains(newTeamName)){
                adapter.add(newTeamName);
            }
        }
    }

}
