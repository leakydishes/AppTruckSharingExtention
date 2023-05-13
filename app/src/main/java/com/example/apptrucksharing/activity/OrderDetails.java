package com.example.apptrucksharing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
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

    Button btn_call_driver;
    private List<DeliveryOrder> list;

    TextView from_sender, from_receiver, drop_off, weight_box, width_box,
            height_box, vehicle_type, goods_type, date_box, location_box,length_box;

    ImageView truck_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        list = new ArrayList<>();
        list = DeliveryOrderDataBase.getDatabase(getApplicationContext()).getDao().getAllData();

        from_sender = findViewById(R.id.from_sender);
        from_receiver = findViewById(R.id.from_receiver);
        drop_off = findViewById(R.id.drop_off);
        date_box = findViewById(R.id.date_box);
        weight_box = findViewById(R.id.weight_box);
        location_box = findViewById(R.id.location_box);
        width_box = findViewById(R.id.width_box);
        length_box = findViewById(R.id.length_box);
        height_box = findViewById(R.id.height_box);
        vehicle_type = findViewById(R.id.vehicle_type);
        goods_type = findViewById(R.id.goods_type);
        truck_Image = findViewById(R.id.truck_Image);

        String name = getIntent().getStringExtra("RECEIVER");
        String date = getIntent().getStringExtra("DATE");
        String location = getIntent().getStringExtra("LOCATION");
        String time = getIntent().getStringExtra("TIME");
        String vehicle = getIntent().getStringExtra("VEHICLE");
        String weight = getIntent().getStringExtra("WEIGHT");
        String width = getIntent().getStringExtra("WIDTH");
        String height = getIntent().getStringExtra("HEIGHT");
        String length = getIntent().getStringExtra("LENGTH");
        String goods = getIntent().getStringExtra("GOODS");

        from_sender.setText(name);
        from_receiver.setText(name);
        date_box.setText(date);
        drop_off.setText(time);
        location_box.setText(location);
        goods_type.setText(goods);
        vehicle_type.setText(vehicle);
        length_box.setText(length);
        width_box.setText(width);
        weight_box.setText(weight);
        height_box.setText(height);

        // Inflate the layout for this fragment
        btn_call_driver = findViewById(R.id.btn_call_driver);
        btn_call_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Call driver");

                //create object
                String msg = "Calling Driver...";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });



    }
}