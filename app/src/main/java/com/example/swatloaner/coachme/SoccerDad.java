package com.example.swatloaner.coachme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SoccerDad extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    UserDataBase userDataBase;
    String userEmail;
    User user;

    //Fields for fragments
    ProfileFragment profileFragment;
    RosterFragment rosterFragment;
//    LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_dad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialize database
        userDataBase = new UserDataBase();

//        loginFragment = new LoginFragment();
//
        profileFragment = new ProfileFragment();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, profileFragment);
        tx.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
        Intent intent;


        //

        switch (item.getItemId())
        {
            case R.id.field:
                Field_Fragment fieldFragment = new Field_Fragment();
                FragmentTransaction tx1 = getSupportFragmentManager().beginTransaction();
                tx1.replace(R.id.content_frame, fieldFragment);
                tx1.commit();
                break;
            case R.id.chat:
                break;
            case R.id.addPlayers:
                break;
            case R.id.notifications:
                break;
            case R.id.newTeam:
                break;
            case R.id.roster:
                rosterFragment = new RosterFragment();
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.content_frame, rosterFragment);
                tx.commit();
                break;
            case R.id.profile:
                profileFragment = new ProfileFragment();
                FragmentTransaction tx2 = getSupportFragmentManager().beginTransaction();
                tx2.replace(R.id.content_frame, rosterFragment);
                tx2.commit();

            default:

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
