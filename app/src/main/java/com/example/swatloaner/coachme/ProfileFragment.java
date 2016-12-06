package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    User user;
    UserDataBase userDataBase;
    ImageView image;
    TextView user_name;
    ListView teams;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> listOfTimes;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //get the current instance of user database and user
//        Intent intent = getActivity().getIntent();
//        userDataBase =  (UserDataBase) intent.getExtras().getSerializable("database");
//        user = (User) intent.getExtras().get("user");

        user = ((SoccerDad) getActivity()).getUser();
        userDataBase = ((SoccerDad) getActivity()).getUserDataBase();

        user_name = (TextView) view.findViewById(R.id.user_name);
        user_name.setText(user.getName());

        teams = (ListView) view.findViewById(R.id.teams);
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.test_list_item, user.getTeams());

        image = (ImageView) view.findViewById(R.id.imageView);
        image.setOnClickListener(this);

        teams.setAdapter(adapter);
        teams.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                user.setCurrentTeamIndex(arg0.getPositionForView(arg1));
            }

        });
        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap)extras.get("data");
            image.setImageResource(0);
            image.setBackground(new BitmapDrawable(getResources(), thumbnail));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == image.getId()) {
            //take picture
            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePicIntent.resolveActivity(getActivity().getPackageManager())!=null) {
                startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

}
