package com.example.apptrucksharing.data.truck;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apptrucksharing.model.Truck;

import java.util.List;

@Dao
public interface TruckDao {
    @Query("SELECT * FROM truck")
    List<Truck> getAll();

    @Insert
    void insert(Truck truck);

    @Query("SELECT * FROM truck WHERE id =:id")
    Truck getFromId(int id);

    @Update
    void update(Truck truck);

    @Delete
    void delete(Truck truck);

}
