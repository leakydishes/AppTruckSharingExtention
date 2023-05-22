package com.example.apptrucksharing.data.truck;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apptrucksharing.model.DeliveryOrder;
import com.example.apptrucksharing.model.Truck;

import java.util.List;

@Dao
public interface TruckDao {
    @Insert
    void insertAllData(Truck truck);
    //Select All Data

    @Query("select * from truck_table")
    List<Truck> getAllData();


    //DELETE DATA
    @Query("delete from truck_table where `id`= :id")
    void deleteData(int id);

    //Update Data
    @Query("update truck_table SET truckName= :truckName ,truckDetail =:truckDetail, drawableResId =:drawableResId where `id`= :id")
    void updateData(String truckName, String truckDetail, int drawableResId, int id);

}
