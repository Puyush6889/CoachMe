package com.example.swatloaner.coachme;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
    FieldFragment fieldFragment;
    RosterFragment rosterFragment;
    NewTeamFragment newTeamFragment;
    ChatFragment chatFragment;
    AddPlayersFragment addPlayersFragment;
    NotificationsFragment notificationsFragment;


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

        //initialize fragments
        profileFragment = new ProfileFragment();
        fieldFragment = new FieldFragment();
        rosterFragment = new RosterFragment();
        newTeamFragment = new NewTeamFragment();
        chatFragment = new ChatFragment();
        addPlayersFragment = new AddPlayersFragment();
        notificationsFragment = new NotificationsFragment();

        //make profile the default fragment
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
        // Handle navigation view item clicks here.();
        Intent intent;

        // Initialize a temporary null fragment
        Fragment fragment = null;

        // Set fragment to the selected one
        switch (item.getItemId())
        {
            case R.id.field:
                fragment = fieldFragment;
                break;
            case R.id.chat:
                fragment = chatFragment;
                break;
            case R.id.addPlayers:
                fragment = addPlayersFragment;
                break;
            case R.id.notifications:
                fragment = notificationsFragment;
                break;
            case R.id.newTeam:
                fragment = newTeamFragment;
                break;
            case R.id.roster:
                fragment = rosterFragment;
                break;
            case R.id.profile:
                fragment = profileFragment;
                break;
            default:
                break;
        }

        // Pull up selected fragment if not null
        if (fragment != null) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, fragment);
            tx.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
