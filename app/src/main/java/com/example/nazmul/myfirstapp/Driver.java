package com.example.nazmul.myfirstapp;

/**
 * Created by mdnum on 11/24/2019.
 */

public class Driver {


    private String id;
    private String name;
    private String contact;
    private String email;

    public Driver(){}

    public Driver(String id, String name, String contact,String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
