package com.example.swatloaner.coachme;


import android.media.tv.TvView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlayersFragment extends Fragment implements View.OnClickListener {

    EditText searchKey;
    Button search;
    Button add;
    ListView playerList;


    private ArrayAdapter<String> adapter;

    User user;
    UserDataBase userDataBase;



    List<String> toAdd;
    List<User> usersToAdd;

    public AddPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_players, container, false);

        searchKey = (EditText) view.findViewById(R.id.searchKey);
        search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(this);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(this);

        toAdd = new ArrayList<>();

        usersToAdd = new ArrayList<>();

        playerList = (ListView) view.findViewById(R.id.playerList);
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.test_list_item, toAdd);
        playerList.setAdapter(adapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO what happens here?
            }
        });

        user = ((SoccerDad) getActivity()).getUser();
        userDataBase = ((SoccerDad) getActivity()).getUserDataBase();

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == search.getId()){
            String email = searchKey.getText().toString();
            User player = userDataBase.getUsers().get(email);
            if (player != null) {
                usersToAdd.add(player);
                adapter.add(player.getName());
            }
        }
        else if (view.getId() == add.getId()){
            if (!usersToAdd.isEmpty()) {
                for (User newby : usersToAdd) {
                    if (!newby.getTeams().contains(user.getCurrentTeam())) {
                        newby.getTeams().add(user.getCurrentTeam());
                    }
                }
                adapter.clear();
                usersToAdd.clear();
            }
        }
    }
}
