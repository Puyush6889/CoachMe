package com.example.swatloaner.coachme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
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
    User user;
    Bundle bundle;

    //Fields for fragments
    ProfileFragment profileFragment;
    //RosterFragment rosterFragment;


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

        Intent intent = getIntent();
        userDataBase = (UserDataBase) intent.getExtras().get("database");
        user = userDataBase.getUsers().get(intent.getExtras().get("user"));

        bundle = new Bundle();
        bundle.putSerializable("database", userDataBase);
        bundle.putSerializable("user", user);

        profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle);
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
//            super.onBackPressed();
            profileFragment = new ProfileFragment();
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, profileFragment);
            tx.commit();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
        Intent intent;


        //

//        switch (item.getItemId())
//        {
//            case R.id.field:
//                Field_Fragment fieldFragment = new Field_Fragment();
//                FragmentTransaction tx1 = getSupportFragmentManager().beginTransaction();
//                tx1.replace(R.id.content_frame, fieldFragment);
//                tx1.commit();
//                break;
//            case R.id.chat:
//                ChatFragment chatFragment = new ChatFragment();
//                FragmentTransaction tx6 = getSupportFragmentManager().beginTransaction();
//                tx6.replace(R.id.content_frame, chatFragment);
//                tx6.commit();
//                break;
//            case R.id.addPlayers:
//                AddPlayersFragment addPlayersFragment = new AddPlayersFragment();
//                FragmentTransaction tx5 = getSupportFragmentManager().beginTransaction();
//                tx5.replace(R.id.content_frame, addPlayersFragment);
//                tx5.commit();
//                break;
//            case R.id.notifications:
//                NotificationsFragment notificationsFragment = new NotificationsFragment();
//                FragmentTransaction tx4 = getSupportFragmentManager().beginTransaction();
//                tx4.replace(R.id.content_frame, notificationsFragment);
//                tx4.commit();
//                break;
//            case R.id.newTeam:
//                NewTeamFragment newTeamFragment = new NewTeamFragment();
//                FragmentTransaction tx3 = getSupportFragmentManager().beginTransaction();
//                tx3.replace(R.id.content_frame, newTeamFragment);
//                tx3.commit();
//                break;
//            case R.id.roster:
//                rosterFragment = new RosterFragment();
//                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//                tx.replace(R.id.content_frame, rosterFragment);
//                tx.commit();
//                break;
//            case R.id.profile:
//                profileFragment = new ProfileFragment();
//                FragmentTransaction tx2 = getSupportFragmentManager().beginTransaction();
//                tx2.replace(R.id.content_frame, profileFragment);
//                tx2.commit();
//
//            default:
//
//        }

        Fragment fragment = null;

        switch (item.getItemId())
        {
            case R.id.field:
                fragment = new Field_Fragment();
                break;
            case R.id.chat:
                fragment = new ChatFragment();
                break;
            case R.id.addPlayers:
                fragment = new AddPlayersFragment();
                break;
//            case R.id.notifications:
//                fragment = new NotificationsFragment();
//                break;
            case R.id.newTeam:
                fragment = new NewTeamFragment();
                break;
            case R.id.roster:
                fragment = new RosterFragment();
                break;
            case R.id.profile:
                fragment = new ProfileFragment();
            default:
                break;
        }

        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, fragment);
            tx.commit();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public User getUser(){
        return user;
    }

    public UserDataBase getUserDataBase(){
        return userDataBase;
    }

}
