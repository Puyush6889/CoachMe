package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

public class MainLogin extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;

    Button signup;
    Button login;

    User user;

    UserDataBase userDataBase;

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

        // need to create userDataBase here
        userDataBase = new UserDataBase();

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
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        intent.putExtra("database",  userDataBase);
        startActivity(intent);
    }
    Toast toast;
    public void login() {
        //Get values to pass to database
        String eml = email.getText().toString();
        int pwrd = Integer.parseInt(password.getText().toString());

        for (User user : userDataBase.getUsers().values()) {
            //scan database for user credentials
            if (user.getEmail().equals(eml) && user.getId() == pwrd) {

                this.user = user;
                Intent intent = new Intent(getApplicationContext(), SoccerDad.class);
                //pass database to soccer dad
                intent.putExtra("database", userDataBase);
                //pass user email to soccer dad so that we can load correct profile
                intent.putExtra("user", this.user.getEmail());
                //start soccer dad activity
                startActivity(intent);

            }

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MainLogin Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
