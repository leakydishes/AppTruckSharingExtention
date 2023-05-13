package com.example.apptrucksharing.data.truck;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apptrucksharing.model.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Truck.class}, version = 1, exportSchema = false)
public abstract class TruckDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "truck_database";
    private static TruckDataBase mINSTANCE;

    public abstract TruckDao truckDao();

    public static synchronized TruckDataBase getInstance(Context context) {
        if (mINSTANCE == null) {
            mINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TruckDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mINSTANCE;
    }
}
