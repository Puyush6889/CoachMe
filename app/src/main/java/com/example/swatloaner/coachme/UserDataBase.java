package com.example.swatloaner.coachme;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joe Turner on 12/2/2016.
 * by Puyush Goel
 */

public class UserDataBase implements  Serializable {

    private HashMap<String, User> users;
    private HashMap<String, String> teams;

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public UserDataBase()
    {
        users = new HashMap<>();
        teams = new HashMap<>();

        put("puyush@vt.edu", new User("Nick", "puyush@vt.edu", 1234, "Team10"));
        put("eqn@vt.edu", new User("Enrique", "eqn@vt.edu", 2341, "Team10"));
        put("joe5@vt.edu", new User("Joe", "joe5@vt.edu", 3412, "Team10"));
        put("kirk5@vt.edu", new User("Kirk", "kirk5@vt.edu", 4123, "Team10"));

        users.get("joe5@vt.edu").getTeams().add("another team");
    }

    public void put(String email, User user){

        users.put(email, user);

        for(String team : user.getTeams()){
            teams.put(team, user.getEmail());
        }

    }

    public String getRoster(String team){
        List<String> emails = new ArrayList<>();
        String roster = "";

        for (User maybeMate : users.values()) {
            for ( String string : maybeMate.getTeams()) {
                if (team.equals(string)) {
                    roster += maybeMate.getName() + "\n"+"\n";
                }
            }
        }
        return roster;
    }

    public List<String> getRosters(User user){
        List<String> rosters = new ArrayList<>();

        for (String t : user.getTeams()){
            rosters.add(getRoster(t));
        }

        return rosters;
    }

}
