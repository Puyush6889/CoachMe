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

    UserDataBase userDataBase;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        Intent intent = new Intent(getApplicationContext(), SoccerDad.class);
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
                Intent intent = new Intent(getApplicationContext(), SoccerDad.class);
                //pass database to soccer dad
                intent.putExtra("database", (Serializable) userDataBase);
                //pass user email to soccer dad so that we can load correct profile
                intent.putExtra("user_email", eml);
                //start soccer dad activity
                startActivity(intent);
            }
        }

        // If you got here the credentials were invalid.

        // Inform the user.

        Context context = getApplicationContext();
        CharSequence text = "INVALID CREDENTIALS";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
