package com.example.apptrucksharing.model;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.apptrucksharing.R;

import java.io.Serializable;

@Entity(tableName = "truck_table")
public class Truck implements Serializable {
    // variables
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "truckName")
    public String truckName;
    @ColumnInfo(name = "truckDetail")
    public String truckDetail;
    @ColumnInfo(name = "drawableResId")
    public int drawableResId;
    // R drawbable is int

    //constructor

    public Truck(String truckName, String truckDetail, int drawableResId) {
        this.truckName = truckName;
        this.truckDetail = truckDetail;
        this.drawableResId = drawableResId;
    }

    // setters
    public int getImageDrawableResId() {
        return drawableResId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleTruck() {
        return truckName;
    }

    public void setTitleTruck(String truckName) {
        this.truckName = truckName;
    }

    public String getDescriptionTruck() {
        return truckDetail;
    }

    public void setDescriptionTruck(String truckDetail) {
        this.truckDetail = truckDetail;
    }

    public int getTruckImage() {
        return drawableResId;
    }

    public void setTruckImage(int truckImage) {
        this.drawableResId = truckImage;
    }


}
