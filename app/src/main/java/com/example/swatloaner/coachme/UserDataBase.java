package com.example.swatloaner.coachme;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Joe Turner on 12/2/2016.
 * by Puyush Goel
 */

public class UserDataBase implements  Serializable {

    private HashMap<String, User> users;

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public UserDataBase()
    {
        users = new HashMap<>();

        put("puyush@vt.edu", new User("Puyush", "puyush@vt.edu", 1234, "Team10"));
        put("eqn@vt.edu", new User("Enrique", "eqn@vt.edu", 2341, "Team10"));
        put("joe5@vt.edu", new User("Joe", "joe5@vt.edu", 3412, "Team10"));
        put("kirk5@vt.edu", new User("Kirk", "krik5@vt.edu", 4123, "Team10"));
    }

    public void put(String email, User user){
        users.put(email, user);
    }

}
