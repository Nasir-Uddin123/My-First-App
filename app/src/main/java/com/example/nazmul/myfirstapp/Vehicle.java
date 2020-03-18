package com.example.nazmul.myfirstapp;

/**
 * Created by mdnum on 9/2/2019.
 */

public class Vehicle {

    private String id;
    private String sL;
    private String eL;
    private String time;

    public Vehicle()
    {

    }

    public Vehicle(String id, String sL, String eL, String time) {
        this.id = id;
        this.sL = sL;
        this.eL = eL;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }

    public String geteL() {
        return eL;
    }

    public void seteL(String eL) {
        this.eL = eL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
