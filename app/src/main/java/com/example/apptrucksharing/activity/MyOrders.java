package com.example.apptrucksharing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.adapters.ClickListener;
import com.example.apptrucksharing.adapters.OrderListAdapter;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.model.DeliveryOrder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends AppCompatActivity implements OrderListAdapter.ItemClickListener{

    public static DeliveryOrderDataBase deliveryOrderDataBase;
    FloatingActionButton deliveryOrder2;
    RecyclerView recyclerview;

    private List<DeliveryOrder> deliveryOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recyclerview = findViewById(R.id.recycler_view_list_orders); // Get list to recycler view

        //variables
        getData();

        // delivery order to go to new delivery screen
        deliveryOrder2 = findViewById(R.id.deliveryOrder2);
        // floating delivery button (+)
        deliveryOrder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Yes, New Delivery button pressed");
                // next step of delivery
                Intent intent = new Intent(getBaseContext(), NewDeliveryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        deliveryOrder = new ArrayList<>();
        deliveryOrder = DeliveryOrderDataBase.getDatabase(getApplicationContext()).getDao().getAllData();
        // Lookup the recyclerview in activity layout
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler_view_list_orders);
        // Create adapter passing in the sample user data
        OrderListAdapter adapter = new OrderListAdapter(deliveryOrder, this, this);

        // Attach the adapter to the recyclerview to populate items
        recyclerview.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onItemClick(DeliveryOrder deliveryOrder) {
        Intent intent = new Intent(MyOrders.this, OrderDetails.class);

        String receiver = deliveryOrder.getReceiverName();
        String sender = deliveryOrder.getSenderName();
        String date = deliveryOrder.getDate();
        String pickUpLocation = deliveryOrder.getPickUpLocation();
        String dropOffLocation = deliveryOrder.getDropOffLocation();
        String pickUpTime = deliveryOrder.getpickUpTime();
        String dropoffTime = deliveryOrder.getdropoffTime();
        String vehicle = deliveryOrder.getVehicleType();
        String weight = deliveryOrder.getWeight();
        String width = deliveryOrder.getWidth();
        String height = deliveryOrder.getHeight();
        String length = deliveryOrder.getLength();
        String goods = deliveryOrder.getGoodType();

        intent.putExtra("RECEIVER", receiver);
        intent.putExtra("SENDER", sender);
        intent.putExtra("DATE", date);
        intent.putExtra("PICKUPLOCATION", pickUpLocation);
        intent.putExtra("DROPOFFLOCATION", dropOffLocation);
        intent.putExtra("PICKUPTIME", pickUpTime);
        intent.putExtra("DROPTIME", dropoffTime);
        intent.putExtra("VEHICLE", vehicle);
        intent.putExtra("WEIGHT", weight);
        intent.putExtra("WIDTH", width);
        intent.putExtra("HEIGHT", height);
        intent.putExtra("LENGTH", length);
        intent.putExtra("GOODS", goods);
        startActivity(intent);
    }
}
