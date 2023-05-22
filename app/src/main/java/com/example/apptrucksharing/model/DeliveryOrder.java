package com.example.apptrucksharing.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "delivery_order")
public class DeliveryOrder {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int key;

    @ColumnInfo(name = "receiverName")
    public String receiverName;

    @ColumnInfo(name = "senderName")
    public String senderName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "pickUpTime")
    public String pickUpTime;

    @ColumnInfo(name = "dropoffTime")
    public String dropoffTime;

    @ColumnInfo(name = "pickUpLocation")
    public String pickUpLocation;

    @ColumnInfo(name = "dropOffLocation")
    public String dropOffLocation;

    @ColumnInfo(name = "goodType")
    public String goodType;

    @ColumnInfo(name = "weight")
    public String weight;

    @ColumnInfo(name = "width")
    public String width;

    @ColumnInfo(name = "length")
    public String length;

    @ColumnInfo(name = "height")
    public String height;

    @ColumnInfo(name = "vehicle")
    public String vehicleType;

    @ColumnInfo(name = "drawableResId")
    public int drawableResId;
    // R drawbable is int

    //setter and getters

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String name) {
        this.senderName = name;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String name) {
        this.receiverName = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getpickUpTime() { return pickUpTime; }

    public void setdropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public String getdropoffTime() { return dropoffTime; }

    public void setpickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) { this.pickUpLocation = pickUpLocation;}

    public String getDropOffLocation() { return dropOffLocation;}

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }
}

