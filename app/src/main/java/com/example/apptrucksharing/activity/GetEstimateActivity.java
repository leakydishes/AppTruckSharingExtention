package com.example.apptrucksharing.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.model.DeliveryOrder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GetEstimateActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map1, map2;
    Button btn_call_driver, pp_btn;
    TextView pick_up_location, drop_off_location, fare_cost, travel_time;
    private List<DeliveryOrder> locations;
    Double fare;

    //phone variable
    private static final int REQUEST_PHONE_CALL = 1;

    // location variable
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    // map variable
    FusedLocationProviderClient fusedLocationClient;

    //paypal variable
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("Insert API here - removed for privacy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_estimate);

        // set to view
        btn_call_driver = findViewById(R.id.btn_call_driver);
        pp_btn = findViewById(R.id.pp_btn);
        pick_up_location = findViewById(R.id.pick_up_location);
        drop_off_location = findViewById(R.id.drop_off_location);
        fare = 50.00; // get fare cost
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // set google maps view
        // get data
        locations = new ArrayList<>();
        locations = DeliveryOrderDataBase.getDatabase(getApplicationContext()).getDao().getAllData();

        // set fragment 1 to screen
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        // set fragment 2 to screen
        SupportMapFragment mapFragment2 = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapView2);
        mapFragment2.getMapAsync(this);


        //get data from previous input
        Intent intent = getIntent();
        String receiverName = intent.getStringExtra("RECEIVER");
        String senderName = intent.getStringExtra("SENDER");
        String date = intent.getStringExtra("DATE");
        String pickUpLocation = intent.getStringExtra("PICKUPLOCATION");
        String dropOffLocation = intent.getStringExtra("DROPOFFLOCATION");
        String pickUpTime = intent.getStringExtra("PICKUPTIME");
        String dropOffTime = intent.getStringExtra("DROPOFFTIME");
        String vehicle = intent.getStringExtra("VEHICLE");
        String weight = intent.getStringExtra("WEIGHT");
        String width = intent.getStringExtra("WIDTH");
        String height = intent.getStringExtra("HEIGHT");
        String length = intent.getStringExtra("LENGTH");
        String goods = intent.getStringExtra("GOODS");

        pick_up_location.setText(pickUpLocation);
        drop_off_location.setText(dropOffLocation);

        // call driver
        btn_call_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Call driver");

                //create object
                String msg1 = "Calling Driver...";
                Toast.makeText(getApplicationContext(), msg1, Toast.LENGTH_SHORT).show();

                // call driver
                String phone = "+34666777888";
                Intent callDriver = new Intent(Intent.ACTION_CALL);
                callDriver.setData(Uri.parse("tel:" + phone));
                if (ContextCompat.checkSelfPermission(GetEstimateActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GetEstimateActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    startActivity(callDriver);
                }
            }
        });

        // call driver
        pp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Call driver");

                //create object
                String msg2 = "Pay with Paypal";
                Toast.makeText(getApplicationContext(), msg2, Toast.LENGTH_SHORT).show();

                //send to paypal
                PayPalPayment transactionPaypal = new PayPalPayment(new BigDecimal(fare),
                        "AUD", "Truck Hire",
                        PayPalPayment.PAYMENT_INTENT_SALE);
                Intent pay = new Intent(getApplicationContext(), PaymentActivity.class);
                pay.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                pay.putExtra(PaymentActivity.EXTRA_PAYMENT, transactionPaypal);
                startActivity(pay);
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        map1 = googleMap;
        // add markers for lost and found items
        for (int i = 0; i < locations.size(); i++) {
            String[] loc = locations.get(i).getPickUpLocation().split(",");
            try {
                LatLng addresses = new LatLng(Double.parseDouble(loc[0]),
                        Double.parseDouble(loc[1]));
                map1.addMarker(new MarkerOptions().position(addresses).
                        title(locations.get(i).getReceiverName()
                                + " " + locations.get(i).getVehicleType()));
                map1.moveCamera(CameraUpdateFactory.newLatLngZoom(addresses, 15));
                //map.moveCamera(CameraUpdateFactory.newLatLng(addresses));
            } catch (Exception exception) {
            }

            map2 = googleMap;
            // add markers for lost and found items
            for (int j = 0; j < locations.size(); j++) {
                String[] loc1 = locations.get(i).getDropOffLocation().split(",");
                try {
                    LatLng addresses = new LatLng(Double.parseDouble(loc1[0]),
                            Double.parseDouble(loc1[1]));
                    map2.addMarker(new MarkerOptions().position(addresses).
                            title(locations.get(j).getSenderName()
                                    + " " + locations.get(j).getVehicleType()));
                    map2.moveCamera(CameraUpdateFactory.newLatLngZoom(addresses, 15));
                    //map.moveCamera(CameraUpdateFactory.newLatLng(addresses));
                } catch (Exception exception) {
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PayPalService.class));
    }
}
