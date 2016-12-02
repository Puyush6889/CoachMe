package com.example.swatloaner.coachme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainLogin extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    EditText password;

    Button signup;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        signup = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()){
            //login
            login();
        }
        else if(view.getId() == signup.getId()){
            //signup
            goToSignUpActivity();
        }
    }

    public void goToSignUpActivity(){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void login(){
        //Get values to pass to database
        String eml = email.getText().toString();
        int pwrd = Integer.parseInt(password.getText().toString());

        //pass eml and pwrd as parameters to database object



    }

}
