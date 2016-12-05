package com.example.swatloaner.coachme;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.swatloaner.coachme.R.id.signup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements  View.OnClickListener {


    EditText email;
    EditText password;
    EditText confirm_password;
    EditText nickName;

    Button signup;

    UserDataBase userDataBase;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signup = (Button) view.findViewById(R.id.signup);
        signup.setOnClickListener(this);


        email = (EditText) view.findViewById(R.id.email);
        nickName = (EditText) view.findViewById(R.id.nickName);
        password = (EditText) view.findViewById(R.id.password);
        Intent intent = getActivity().getIntent();
        confirm_password = (EditText) view.findViewById(R.id.confirm_password);

        userDataBase = (UserDataBase) intent.getExtras().get("database");

        return  view;
    }

    /*
        Sign up method will check the database for duplicates and add the user to it if no duplicates
     */
    private void signUp(){

        String name = nickName.getText().toString();
        String userEmail = email.getText().toString();
        for(User user: userDataBase.getUsers().values())
        {
            if ( userEmail.equals(user.getEmail()))
            {
                Context context = getContext();
                CharSequence text = "Email in use, Please enter a different one";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //added return so that function stops here ~ Joe
                return;
            }
        }

        Integer userPassword = Integer.parseInt(password.getText().toString());
        Integer userConfirm_Password = Integer.parseInt(confirm_password.getText().toString());

        //check if passwords match
        if (userPassword != userConfirm_Password){
            Context context = getContext();
            CharSequence text = "Passwords do not match";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //return so that code stops
            return;
        }

        // Create new user
        User user = new User(name, userEmail, userConfirm_Password);
        // Add user to database
        userDataBase.put(userEmail, user);

        //create intent to go to profile activity
        Intent intent = new Intent(getContext(), ProfileFragment.class);
        //profile activty will need access to the User Database
        intent.putExtra("database", userDataBase);
        //profile activity will need to have a way to know which profile to load
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == signup.getId()){
            signUp();
        }
    }

}
