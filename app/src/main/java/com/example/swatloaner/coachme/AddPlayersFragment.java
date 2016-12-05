package com.example.swatloaner.coachme;


import android.media.tv.TvView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlayersFragment extends Fragment implements View.OnClickListener,
        //Not sure if this is what we need here...
        TvView.OnUnhandledInputEventListener{

    SearchView search;
    ListView playerList;

    public AddPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_players, container, false);

        search = (SearchView) view.findViewById(R.id.search);


        return view;
    }

    @Override
    public void onClick(View view) {

    }


    //Not sure if this is what we need here...
    @Override
    public boolean onUnhandledInputEvent(InputEvent inputEvent) {
        return false;
    }
}
