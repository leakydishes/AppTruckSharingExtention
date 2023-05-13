package com.example.apptrucksharing.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptrucksharing.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewDeliveryActivity extends AppCompatActivity {

    //set up variables
    Calendar calendar;
    CalendarView calendarView;
    Date date;
    String dateFormatObject;
    Button next;

    EditText receiver_name, pick_up_time, pick_up_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery);

        // set calender information
        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.YEAR, 2023);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        calendarView = findViewById(R.id.calendarView); // Initialize calendarView here
        calendar = Calendar.getInstance();
        date = calendar.getTime();
        System.out.println("current date : " + date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                Log.v("Date picker engaged", "Yes, you have selected a date");
            }
        });

        SimpleDateFormat dateFormatObject = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        receiver_name = findViewById(R.id.receiver_name);
        pick_up_time = findViewById(R.id.pick_up_time);
        pick_up_location = findViewById(R.id.pick_up_location);

        //reset variables
//        receiver_name.setText("");
//        pick_up_time.setText("");
//        pick_up_location.setText("");


        next = findViewById(R.id.next); // Initialize button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Yes, Next Button pressed");

                // get user information
                String receiverName = receiver_name.getText().toString().trim();
                String pickUpTime = pick_up_time.getText().toString().trim();
                String pickUpLocation = pick_up_location.getText().toString().trim();
                String pickUpDate = String.valueOf(dateFormatObject.format(calendarView.getDate()).trim());

                // bundle data for next fragment
                Bundle bundle = new Bundle();
                bundle.putString("receiverName", receiverName);
                bundle.putString("pickUpTime", pickUpTime);
                bundle.putString("pickUpLocation", pickUpLocation);
                bundle.putString("pickUpDate", pickUpDate);

                //create object
                String msg = "Data added to database";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                // next step of delivery
                Intent intent = new Intent(getBaseContext(), CreateOrderActivity.class);
                intent.putExtra("receiverName", receiverName);
                intent.putExtra("pickUpTime", pickUpTime);
                intent.putExtra("pickUpLocation", pickUpLocation);
                intent.putExtra("pickUpDate", pickUpDate);
                startActivity(intent);
            }
        });
    }
}