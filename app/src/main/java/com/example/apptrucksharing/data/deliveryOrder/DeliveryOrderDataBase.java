package com.example.apptrucksharing.data.deliveryOrder;

import android.content.Context;
import android.database.Cursor;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apptrucksharing.data.truck.TruckDao;
import com.example.apptrucksharing.data.truck.TruckDataBase;
import com.example.apptrucksharing.model.DeliveryOrder;
import com.example.apptrucksharing.model.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
returns access to object DeliveryOrderDao
 */

@Database(entities={DeliveryOrder.class}, version =1, exportSchema = false)
public abstract class DeliveryOrderDataBase extends RoomDatabase{

    public abstract DeliveryOrderDao getDao();

    private static DeliveryOrderDataBase instance;

    public static DeliveryOrderDataBase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (DeliveryOrderDataBase.class) {
                instance = Room.databaseBuilder(context, DeliveryOrderDataBase.class, "DATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;
    }

}