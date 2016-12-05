package com.example.swatloaner.coachme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    User user;
    UserDataBase userDataBase;
    TextView user_name;
    ListView teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();

        userDataBase =  (UserDataBase) intent.getExtras().getSerializable("database");
        String userEmail = (String) intent.getExtras().get("user_email");
        user = userDataBase.getUsers().get(userEmail);

        user_name = (TextView) findViewById(R.id.user_name);
        user_name.setText(user.getName());

        teams = (ListView) findViewById(R.id.teams);
        teams.setOnItemClickListener(this);



    }

    @Override
    public void onClick(View view) {
        //go to roster of team on click of team name
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        user.setCURRENT_TEAM(i);
    }
}
