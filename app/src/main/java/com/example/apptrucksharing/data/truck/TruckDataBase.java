package com.example.apptrucksharing.data.truck;

import static com.example.apptrucksharing.activity.HomeActivity.truckDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDao;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.model.Truck;

import java.util.concurrent.Executors;

@Database(entities = {Truck.class}, version = 4, exportSchema = false)
public abstract class TruckDataBase extends RoomDatabase {
    public abstract TruckDao getDao();

    private static TruckDataBase instance;
    private static final String DATABASE_NAME = "truck_database";

    public static TruckDataBase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (TruckDataBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                            TruckDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            }
        }
        return instance;
    }

}