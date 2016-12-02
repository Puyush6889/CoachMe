package com.example.swatloaner.coachme;

import java.util.HashMap;

/**
 * Created by Puyush Goel on 12/2/2016.
 */

public class User {

    private String name;

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

    private String email;
    private Integer id;
    public void User(String name, String email, Integer id)
    {
        this.name = name;
        this.email = email;
        this.id = id;
    }
}
