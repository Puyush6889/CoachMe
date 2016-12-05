package com.example.swatloaner.coachme;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Puyush Goel on 12/2/2016.
 */

public class User implements Serializable{

    private String name;
    private String email;
    private Integer id;
    private List<String> teams;
    private Bitmap profilePic;

    public int getCURRENT_TEAM() {
        return CURRENT_TEAM;
    }

    public void setCURRENT_TEAM(int CURRENT_TEAM) {
        this.CURRENT_TEAM = CURRENT_TEAM;
    }

    private int CURRENT_TEAM;

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeam(List<String> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public User(String name, String email, Integer id, String team)
    {
        teams = new ArrayList<>();
        this.name = name;
        this.email = email;
        this.id = id;
        this.teams.add(team);
        CURRENT_TEAM = 0;
    }

    public User(String name, String email, Integer id)
    {
        teams = new ArrayList<>();
        this.name = name;
        this.email = email;
        this.id = id;
        CURRENT_TEAM = 0;
    }


}
