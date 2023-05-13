package com.example.apptrucksharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.apptrucksharing.R;
import com.example.apptrucksharing.data.truck.TruckDao;
import com.example.apptrucksharing.data.truck.TruckDataBase;
import com.example.apptrucksharing.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static TruckDataBase truckDatabase;

    public static TruckDao truckDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initialise truck database & allow on main thread
        truckDatabase = Room.databaseBuilder(getApplicationContext(),
                        TruckDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        // start fragment home
        Fragment fragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "home_fragment");
        transaction.commit();
    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    // cases for menus
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
                // already at home return true
                return true;
            case R.id.item2:
                Toast.makeText(this, "Account selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "My Orders selected", Toast.LENGTH_SHORT).show();
                // open new activity My Orders
                startActivity(new Intent(HomeActivity.this, MyOrders.class));
                //startActivity(new Intent(HomeActivity.this, OrderDetails.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}