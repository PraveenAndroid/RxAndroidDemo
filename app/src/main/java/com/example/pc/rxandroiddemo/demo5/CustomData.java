package com.example.pc.rxandroiddemo.demo5;

/**
 * Created by pc on 8/26/2018.
 */

public class CustomData {

    public int id;
    public String name;
    public String mobile;

    public CustomData(int id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
