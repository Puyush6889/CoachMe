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
    LoginFragment loginFragment;


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

        loginFragment = new LoginFragment();


//        if (user == null){
//            //go to login fragment to get a user
//            //That is, set the default fragment to the login
//            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//            tx.replace(R.id.content_frame, loginFragment);
//            tx.commit();
//
//            //login will start signup fragment if no user
//
//
//        }
//        else{ //user is not null and is signed up/in
//            //go to profile fragment
//
//
//        }

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, loginFragment);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.soccer_dad, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                intent = new Intent(getApplication(), Field_Draw.class);
                startActivity(intent);
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
                break;
//            case R.id.profile:
//                intent = new Intent(getApplication(), Profile.class);
//                startActivity(intent);
            default:

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
