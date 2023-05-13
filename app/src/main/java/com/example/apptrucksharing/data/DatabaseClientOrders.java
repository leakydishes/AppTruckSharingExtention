package com.example.apptrucksharing.data;

import android.content.Context;

import androidx.room.Room;

import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.data.truck.TruckDataBase;

public class DatabaseClientOrders {

    private Context mCtx2;
    private static DatabaseClientOrders mInstance2;

    //our app database object
    private DeliveryOrderDataBase deliveryOrderDataBase;

    private DatabaseClientOrders(Context mCtx2) {
        this.mCtx2 = mCtx2;

        //creating the app database with Room database builder
        deliveryOrderDataBase = Room.databaseBuilder(mCtx2, DeliveryOrderDataBase.class, "MyOrders").build();
    }

    public static synchronized DatabaseClientOrders getInstance(Context mCtx2) {
        if (mInstance2 == null) {
            mInstance2 = new DatabaseClientOrders(mCtx2);
        }
        return mInstance2;
    }

    public DeliveryOrderDataBase getAppDatabase() {
        return deliveryOrderDataBase;
    }
}