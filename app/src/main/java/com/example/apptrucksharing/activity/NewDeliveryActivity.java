package com.example.apptrucksharing.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptrucksharing.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewDeliveryActivity extends AppCompatActivity {

    //set up variables
    Calendar calendar;
    CalendarView calendarView;
    Date date;
    Button next;

    LocationListener locationListener;
    LocationManager locationManager;
    LocationListener locationListener2;
    LocationManager locationManager2;

    EditText receiver_name, sender_name, pick_up_time, drop_off_time,
            pick_up_location, drop_off_location;

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
        sender_name = findViewById(R.id.sender_name);
        pick_up_time = findViewById(R.id.pick_up_time);
        drop_off_time = findViewById(R.id.drop_off_time);
        pick_up_location = findViewById(R.id.pick_up_location);
        drop_off_location = findViewById(R.id.drop_off_location);

        Places.initialize(getApplicationContext(), "Insert API here - removed for privacy");
        PlacesClient places = Places.createClient(this);
        PlacesClient places2 = Places.createClient(this);

        // place fragment
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,
                Place.Field.ADDRESS, Place.Field.LAT_LNG));

        // place fragment
        AutocompleteSupportFragment autocompleteSupportFragment2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment2);
        autocompleteSupportFragment2.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,
                Place.Field.ADDRESS, Place.Field.LAT_LNG));

        // click listener 1
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            // error message
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(NewDeliveryActivity.this, "Error Found: "+status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LatLng latlng = place.getLatLng();
                drop_off_location.setText(latlng.latitude+","+ latlng.longitude);
            }
        });

        // click listener 2
        autocompleteSupportFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            // error message
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(NewDeliveryActivity.this, "Error Found: "+status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LatLng latlng = place.getLatLng();
                pick_up_location.setText(latlng.latitude+","+ latlng.longitude);
            }
        });

        // set location services 1
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                pick_up_location.setText(location.getLatitude()+","+location.getLongitude());
            }
        };

        // set location services 2
        locationManager2 = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener2 = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                drop_off_location.setText(location.getLatitude()+","+location.getLongitude());
            }
        };


        next = findViewById(R.id.next); // Initialize button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Yes, Next Button pressed");

                // get user information
                String receiverName = receiver_name.getText().toString().trim();
                String senderName = sender_name.getText().toString().trim();
                String pickUpTime = pick_up_time.getText().toString().trim();
                String dropOffTime = drop_off_time.getText().toString().trim();
                String pickUpLocation = pick_up_location.getText().toString().trim();
                String dropOffLocation = drop_off_location.getText().toString().trim();
                String pickUpDate = String.valueOf(dateFormatObject.format(calendarView.getDate()).trim());

                // bundle data for next fragment
                Bundle bundle = new Bundle();
                bundle.putString("receiverName", receiverName);
                bundle.putString("senderName", senderName);
                bundle.putString("pickUpTime", pickUpTime);
                bundle.putString("dropOffTime", dropOffTime);
                bundle.putString("pickUpLocation", pickUpLocation);
                bundle.putString("dropOffLocation", dropOffLocation);
                bundle.putString("pickUpDate", pickUpDate);

                //create object
                String msg = "Data added to database";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                // next step of delivery
                Intent intent = new Intent(getBaseContext(), CreateOrderActivity.class);
                intent.putExtra("receiverName", receiverName);
                intent.putExtra("senderName", senderName);
                intent.putExtra("pickUpTime", pickUpTime);
                intent.putExtra("dropOffTime", dropOffTime);
                intent.putExtra("pickUpLocation", pickUpLocation);
                intent.putExtra("dropOffLocation", dropOffLocation);
                intent.putExtra("pickUpDate", pickUpDate);
                startActivity(intent);
            }
        });
    }
}