package com.example.apptrucksharing.data;

import android.content.Context;

import androidx.room.Room;

import com.example.apptrucksharing.data.truck.TruckDataBase;

import java.io.Serializable;

public class DatabaseClient implements Serializable {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private TruckDataBase truckDataBase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        truckDataBase = Room.databaseBuilder(mCtx, TruckDataBase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public TruckDataBase getAppDatabase() {
        return truckDataBase;
    }
}