package com.example.apptrucksharing.fragments;

import static com.example.apptrucksharing.activity.HomeActivity.truckDao;
import static com.example.apptrucksharing.activity.HomeActivity.truckDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apptrucksharing.R;
import com.example.apptrucksharing.activity.CreateOrderActivity;
import com.example.apptrucksharing.activity.NewDeliveryActivity;
import com.example.apptrucksharing.data.DatabaseClient;
import com.example.apptrucksharing.data.truck.TruckDao;
import com.example.apptrucksharing.data.truck.TruckDataBase;
import com.example.apptrucksharing.model.Truck;
import com.example.apptrucksharing.adapters.TruckListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FloatingActionButton deliveryOrder;
    TextView logo;

    RecyclerView recyclerView;
    TruckListAdapter truckListAdapter;

    // variables for order
    private List<Truck> truckList;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false); // inflate layout for this fragment
        logo = view.findViewById(R.id.logo);
        deliveryOrder = view.findViewById(R.id.deliveryOrder);

        // add menu button here

        // floating delivery button (+)
        deliveryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Yes, New Delivery button pressed");
                // first step of delivery
                Intent intent = new Intent(getActivity(), NewDeliveryActivity.class);
                startActivity(intent);
            }
        });

        //list of users to database
        List<Truck> truck = truckDatabase.truckDao().getAll();

        RecyclerView recyclerview = view.findViewById(R.id.recycler_view_list_trucks); // Get list of trucks to recycler view

        int[] vanImages = {R.drawable.van1, R.drawable.van2, R.drawable.van3, R.drawable.van4};
        String[] vanNames = getResources().getStringArray(R.array.truck_names);
        // get news descriptions
        String[] vanDescription = getResources().getStringArray(R.array.truck_descriptions);

        // insert user
        // iterate through each string and int to array list to show image, title, description
        for (int i = 0; i < vanNames.length; i++) {
            truckDatabase.truckDao().insert(new Truck(vanNames[i], vanDescription[i], vanImages[i]));
        }

        // create new listener with adapter and layout
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        TruckListAdapter truckListAdapter;
        truckListAdapter = new TruckListAdapter(truck, requireContext());
        recyclerview.setAdapter(truckListAdapter);


        return view;
    }

}