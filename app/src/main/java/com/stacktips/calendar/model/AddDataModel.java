package com.stacktips.calendar.model;

import java.io.Serializable;

/**
 * Created by User on 3/13/2018.
 */

public class AddDataModel implements Serializable {

    String name, email, password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
