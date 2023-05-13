package com.example.apptrucksharing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.data.deliveryOrder.DeliveryOrderDataBase;
import com.example.apptrucksharing.fragments.HomeFragment;
import com.example.apptrucksharing.model.DeliveryOrder;

public class CreateOrderActivity extends AppCompatActivity {

    //set up variables
    EditText input_weight, input_width, input_length, input_height;
    RadioGroup radio_group_good_type1, radio_group_good_type2;
    RadioGroup radio_group_vehicle_type1, radio_group_vehicle_type2, input_other_vehicle;
    Button create_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        //get data from previous input
        Intent intent = getIntent();

        String receiverName = intent.getStringExtra("receiverName");
        String pickUpTime = intent.getStringExtra("pickUpTime");
        String pickUpLocation = intent.getStringExtra("pickUpLocation");
        String pickUpDate = intent.getStringExtra("pickUpDate");

        radio_group_good_type1 = findViewById(R.id.radio_group_good_type1);
        radio_group_good_type2 = findViewById(R.id.radio_group_good_type2);
        radio_group_vehicle_type1 = findViewById(R.id.radio_group_vehicle_type1);
        radio_group_vehicle_type2 = findViewById(R.id.radio_group_vehicle_type2);

        input_weight = findViewById(R.id.input_weight);
        input_width = findViewById(R.id.input_width);
        input_length = findViewById(R.id.input_length);
        input_height = findViewById(R.id.input_height);


        // set button to Home fragment/ screen
        create_order = findViewById(R.id.create_order);
        create_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Yes, New Delivery button pressed");
                String goodType = null;
                String vehicleType = null;
                if (radio_group_good_type1 != null) {
                    int id=radio_group_good_type1.getCheckedRadioButtonId();
                    RadioButton rb=(RadioButton) findViewById(id);
                    goodType=rb.getText().toString().trim();
                    String msg = goodType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
                else if (radio_group_good_type2 != null) {
                    int id=radio_group_good_type2.getCheckedRadioButtonId();
                    RadioButton rb2=(RadioButton) findViewById(id);
                    goodType=rb2.getText().toString().trim();
                    String msg2 = goodType;
                    Toast.makeText(getApplicationContext(), msg2, Toast.LENGTH_SHORT).show();
                }

                if (radio_group_vehicle_type1 != null) {
                    int id=radio_group_vehicle_type1.getCheckedRadioButtonId();
                    RadioButton rb3=(RadioButton) findViewById(id);
                    vehicleType=rb3.getText().toString().trim();
                    String msg3 = vehicleType;
                    Toast.makeText(getApplicationContext(), msg3, Toast.LENGTH_SHORT).show();
                }
                else if (radio_group_vehicle_type2 != null) {
                    int id=radio_group_vehicle_type2.getCheckedRadioButtonId();
                    RadioButton rb4=(RadioButton) findViewById(id);
                    vehicleType=rb4.getText().toString().trim();
                    String msg4 = vehicleType;
                    Toast.makeText(getApplicationContext(), msg4, Toast.LENGTH_SHORT).show();
                }

                // get user information
                String inputWeight = input_weight.getText().toString().trim();
                String inputWidth = input_width.getText().toString().trim();
                String inputLength = input_length.getText().toString().trim();
                String inputHeight = input_height.getText().toString().trim();

                //Create new object of database
                DeliveryOrder model = new DeliveryOrder();
                //previous screen data
                model.setName(receiverName);
                model.setTime(pickUpTime);
                model.setLocation(pickUpLocation);
                model.setDate(pickUpDate);

                // this screen data
                model.setWeight(inputWeight);
                model.setWidth(inputWidth);
                model.setLength(inputLength);
                model.setHeight(inputHeight);
                model.setGoodType(goodType);
                model.setVehicleType(vehicleType);

                // insert into database
                DeliveryOrderDataBase.getDatabase(getApplicationContext()).getDao().insertAllData(model);

                //reset variables
                input_weight.setText("");
                input_width.setText("");
                input_length.setText("");
                input_height.setText("");
                String msg5 = "Data added to database deliveryOrder";
                Toast.makeText(getApplicationContext(), msg5, Toast.LENGTH_SHORT).show();

                //navigate back to home
                // next step of delivery
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);

            }
        });
    }
}