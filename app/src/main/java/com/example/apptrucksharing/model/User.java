package com.example.apptrucksharing.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// model class to get user information and hold data
// id is primary key
@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String userName;
    private String name;
    private String password;
    private int phoneNumber;

    // constructor
    public User(String userName, String name, String password, int phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    //getter setters
    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}



