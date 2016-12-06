package com.example.swatloaner.coachme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link add_player_fieldFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link add_player_fieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_player_fieldFragment extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    User currentUser;
    UserDataBase UDB;
    Button team;
    Button opponent;
    ListView list;
    ArrayAdapter adapter;
    String currentChoice;


    public add_player_fieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_player_fieldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static add_player_fieldFragment newInstance(String param1, String param2) {
        add_player_fieldFragment fragment = new add_player_fieldFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_player_field, container, false);
        currentUser = ((SoccerDad) getActivity()).getUser();
        UDB = ((SoccerDad) getActivity()).getUserDataBase();
        team = (Button) view.findViewById(R.id.addtoteam);
        opponent = (Button) view.findViewById(R.id.addtoteam);
        list = (ListView) view.findViewById(R.id.listAddPlayerField);
        List<String> playerList = UDB.getRoster(currentUser.getCurrentTeam());
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.test_list_item, playerList);
        list.setAdapter(adapter);
        currentChoice = "";
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentChoice = (String) adapterView.getItemAtPosition(i);
            }
        });

        return view;
    }
    public String getCurrentChoice() {
        return currentChoice;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == team.getId()){
            //add a blue circle
        }
        if (view.getId() == opponent.getId()) {
            //add a red circle
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
