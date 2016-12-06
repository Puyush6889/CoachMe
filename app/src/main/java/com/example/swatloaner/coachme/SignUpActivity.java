package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    EditText password;
    EditText confirm_password;
    EditText nickName;
    EditText teamName;

    Button signup;

    UserDataBase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);

        teamName = (EditText)findViewById(R.id.teamName);
        email = (EditText) findViewById(R.id.email);
        nickName = (EditText) findViewById(R.id.nickName);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

        userDataBase = (UserDataBase) getIntent().getExtras().get("database");
    }

    /*
        Sign up method will check the database for duplicates and add the user to it if no duplicates
     */
    private void signUp(){

        String name = nickName.getText().toString();
        String userEmail = email.getText().toString();
        String userTeam = teamName.getText().toString();
        for(User user: userDataBase.getUsers().values())
        {
            if ( userEmail.equals(user.getEmail()))
            {
                Context context = getApplicationContext();
                CharSequence text = "Email in use, Please enter a different one";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //added return so that function stops here ~ Joe
                return;
            }
        }

        int userPassword = Integer.parseInt(password.getText().toString());
        int userConfirm_Password = Integer.parseInt(confirm_password.getText().toString());

        //check if passwords match
        if (userPassword != userConfirm_Password){
            Context context = getApplicationContext();
            CharSequence text = "Passwords do not match";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //return so that code stops
            return;
        }

        // Create new user
        User user = new User(name, userEmail, userConfirm_Password, userTeam);
        // Add user to database
        userDataBase.put(userEmail, user);

        //create intent to go to profile activity
        Intent intent = new Intent(getApplicationContext(), SoccerDad.class);
        //profile activty will need access to the User Database
        intent.putExtra("database", userDataBase);
        //profile activity will need to have a way to know which profile to load
        intent.putExtra("user", user);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == signup.getId()){
            signUp();
        }
    }
}
