package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText email;
    EditText password;

    Button signup;
    Button login;

    UserDataBase userDataBase;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signup = (Button) view.findViewById(R.id.signup);
        login = (Button) view.findViewById(R.id.login);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        // need to create userDataBase here
        userDataBase = new UserDataBase();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        return view;
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            //login
            login();
        } else if (view.getId() == signup.getId()) {
            //signup
            goToSignUpActivity();
        }
    }

    public void goToSignUpActivity() {
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        intent.putExtra("database", (Serializable) userDataBase);
        startActivity(intent);
    }

    public void login() {
        //Get values to pass to database
        String eml = email.getText().toString();
        int pwrd = Integer.parseInt(password.getText().toString());

        for (User user : userDataBase.getUsers().values()) {
            Log.e("Check for user", user.getName());
            Log.e("Check for user", user.getEmail());
            //scan database for user credentials
            if (user.getEmail().equals(eml) && user.getId() == pwrd) {
                Intent intent = new Intent(getActivity(), Profile.class);
                //pass database to soccer dad
                intent.putExtra("database", (Serializable) userDataBase);
                //pass user email to soccer dad so that we can load correct profile
                intent.putExtra("user_email", eml);
                //start soccer dad activity
                startActivity(intent);

            }
            else
            {
//                Context context = getActivity();
//                CharSequence text = "INVALID CREDENTIALS";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
            }

        }

        // If you got here the credentials were invalid.

        // Inform the user.


    }

}
