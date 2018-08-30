package com.example.pc.rxandroiddemo.flatmap;

import android.location.Address;

/**
 * Created by pc on 8/28/2018.
 */

public class User {

    public String name,gender;
    public UserAddress address;

    public User(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }
}
