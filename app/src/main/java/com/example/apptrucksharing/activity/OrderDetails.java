package com.example.apptrucksharing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.fragments.HomeFragment;
import com.example.apptrucksharing.model.DeliveryOrder;

import java.util.ArrayList;
import java.util.List;


public class OrderDetails extends AppCompatActivity {

    Button btn_get_estimate;

    private List<DeliveryOrder> deliveryOrder;

    TextView from_sender, from_receiver, drop_off_time, pick_up_time, weight_box, width_box,
            height_box, vehicle_type, goods_type, date_box, pick_up_location,drop_off_location,
            length_box;

    ImageView truck_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        from_sender = findViewById(R.id.from_sender);
        from_receiver = findViewById(R.id.from_receiver);
        drop_off_time = findViewById(R.id.drop_off_time);
        pick_up_time = findViewById(R.id.pick_up_time);
        date_box = findViewById(R.id.date_box);
        weight_box = findViewById(R.id.weight_box);
        pick_up_location = findViewById(R.id.pick_up_location);
        drop_off_location = findViewById(R.id.drop_off_location);
        width_box = findViewById(R.id.width_box);
        length_box = findViewById(R.id.length_box);
        height_box = findViewById(R.id.height_box);
        vehicle_type = findViewById(R.id.vehicle_type);
        goods_type = findViewById(R.id.goods_type);
        truck_Image = findViewById(R.id.truck_Image);

        String receiverName = getIntent().getStringExtra("RECEIVER");
        String senderName = getIntent().getStringExtra("SENDER");
        String date = getIntent().getStringExtra("DATE");
        String pickUpLocation = getIntent().getStringExtra("PICKUPLOCATION");
        String dropOffLocation = getIntent().getStringExtra("DROPOFFLOCATION");
        String pickUpTime = getIntent().getStringExtra("PICKUPTIME");
        String dropOffTime = getIntent().getStringExtra("DROPTIME");
        String vehicle = getIntent().getStringExtra("VEHICLE");
        String weight = getIntent().getStringExtra("WEIGHT");
        String width = getIntent().getStringExtra("WIDTH");
        String height = getIntent().getStringExtra("HEIGHT");
        String length = getIntent().getStringExtra("LENGTH");
        String goods = getIntent().getStringExtra("GOODS");

        from_sender.setText(senderName);
        from_receiver.setText(receiverName);
        date_box.setText(date);
        pick_up_time.setText(pickUpTime);
        drop_off_time.setText(dropOffTime);
        pick_up_location.setText(pickUpLocation);
        drop_off_location.setText(dropOffLocation);
        goods_type.setText(goods);
        vehicle_type.setText(vehicle);
        length_box.setText(length);
        width_box.setText(width);
        weight_box.setText(weight);
        height_box.setText(height);


        // Inflate the layout for this fragment
        btn_get_estimate = findViewById(R.id.btn_get_estimate);
        btn_get_estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Get Estimate");

                //create object
                String msg = "Get Estimate";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OrderDetails.this, GetEstimateActivity.class);
                intent.putExtra("RECEIVER", receiverName);
                intent.putExtra("SENDER", senderName);
                intent.putExtra("DATE", date);
                intent.putExtra("PICKUPLOCATION", pickUpLocation);
                intent.putExtra("DROPOFFLOCATION", dropOffLocation);
                intent.putExtra("PICKUPTIME", pickUpTime);
                intent.putExtra("DROPOFFTIME", dropOffTime);
                intent.putExtra("VEHICLE", vehicle);
                intent.putExtra("WEIGHT", weight);
                intent.putExtra("WIDTH", width);
                intent.putExtra("HEIGHT", height);
                intent.putExtra("LENGTH", length);
                intent.putExtra("GOODS", goods);
                startActivity(intent);
            }
        });



    }
}