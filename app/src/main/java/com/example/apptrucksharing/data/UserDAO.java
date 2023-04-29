package com.example.apptrucksharing.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apptrucksharing.model.User;
import com.example.apptrucksharing.model.User;

import java.io.Serializable;

// interface to map sequences/ query's and functions
@Dao
public interface UserDAO extends Serializable {

    //query to database (SQL) if user is in database
    @Query("SELECT * FROM User WHERE email = :name = :email and password = :password")
    User getUser(String name, String password);
    @Insert
    void insert(User user);
}
