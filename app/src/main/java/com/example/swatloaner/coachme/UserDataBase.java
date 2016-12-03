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
        user1 = new User("Puyush", "puyush@vt.edu", 1234);
        user2 = new User("Enq", "eqn@vt.edu", 2341);
        user3 = new User("Joe", "joep5@vt.edu", 3412);
        user4 = new User("kirck", "krick5@vt.edu", 4123);
    }

    public void fillUserDatabase()
    {
        users.put(user1.getName(), user1);
        users.put(user2.getName(), user2);
        users.put(user3.getName(), user3);
        users.put(user4.getName(), user4);
        for (User name : users6.values()) {
            users.put(name.getName(), name);
        }

    }
}
