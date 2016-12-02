package com.example.swatloaner.coachme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    EditText password;
    EditText confirm_password;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
    }

    /*
        Sign up method will check the database for duplicates and add the user to it if no duplicates
     */
    private void signUp(){
        //pull values from edit ext views

        //pass them to search function hashtable

        //create a new user object and add it to the table
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == signup.getId()){
            signUp();
        }
    }
}
