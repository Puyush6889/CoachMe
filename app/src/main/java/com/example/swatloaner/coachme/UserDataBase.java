package com.example.swatloaner.coachme;

import java.util.HashMap;

/**
 * Created by Joe Turner on 12/2/2016.
 * by Puyush Goel
 */

public class UserDataBase {
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private HashMap<String, User> users;
    private HashMap<String, User> users6;

    public HashMap<String, User> getUsersDatabase() {

        this.users = users6;
        return users;
    }

    public void setUsersDatabase(HashMap<String, User> users) {
        this.users6 = users;
    }

    public UserDataBase()
    {
        users = new HashMap<>();
        users6 = new HashMap<>();
        user1 = new User("puyush@vt.edu", "puyush@vt.edu", 1234);
        user2 = new User("eqn@vt.edu", "eqn@vt.edu", 2341);
        user3 = new User("joep5@vt.edu", "joep5@vt.edu", 3412);
        user4 = new User("krick5@vt.edu", "krick5@vt.edu", 4123);
    }

    public void fillUserDatabase()
    {
        users.put(user1.getEmail(), user1);
        users.put(user2.getEmail(), user2);
        users.put(user3.getEmail(), user3);
        users.put(user4.getEmail(), user4);
        for (User name : users6.values()) {
            users.put(name.getEmail(), name);
        }

    }
}
