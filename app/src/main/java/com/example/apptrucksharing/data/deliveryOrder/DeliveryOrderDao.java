package com.example.apptrucksharing.data.deliveryOrder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apptrucksharing.model.DeliveryOrder;

import java.util.List;

/*
    Order interface
 */

@Dao
public interface DeliveryOrderDao {

    @Insert
    void insertAllData(DeliveryOrder model);

    //Select All Data
    @Query("select * from  delivery_order")
    List<DeliveryOrder> getAllData();

    //DELETE DATA
    @Query("delete from delivery_order where `key`= :id")
    void deleteData(int id);

    //Update Data

    @Query("update delivery_order SET receiverName= :receiverName, senderName= :senderName , " +
            "pickUpTime= :pickUpTime, dropoffTime =:dropoffTime, pickUpLocation =:pickUpLocation," +
            "dropOffLocation =:dropOffLocation where `key`= :key")
    void updateData(String receiverName, String senderName, String dropoffTime, String pickUpTime,
                    String pickUpLocation, String dropOffLocation, int key);


    @Query("update delivery_order SET weight= :weight ,width =:width, length =:length, " +
            "height =:height, vehicle =:vehicle where `key`= :key")
    void updateDataDetails(String weight, String width, String length, String height,
                           String vehicle, int key);

}
