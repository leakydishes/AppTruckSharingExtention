package com.example.apptrucksharing.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptrucksharing.R;
import com.example.apptrucksharing.activity.MyOrders;
import com.example.apptrucksharing.activity.OrderDetails;
import com.example.apptrucksharing.model.DeliveryOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {
    List<DeliveryOrder> list;
    private ItemClickListener clickListener; // interface global

    public OrderListAdapter(List<DeliveryOrder> list, MyOrders clickListener) {
        this.list = list;
        this.clickListener = (ItemClickListener) clickListener;
    }

    @NonNull
    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_list, parent,false);
        return new MyViewHolder(view); //return view instance of the recycler
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, int position) {
        holder.toReceiver.setText(list.get(position).getVehicleType());
        holder.date.setText(list.get(position).getDate());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(list.get(position).getTime());
        holder.vehicle.setText(list.get(position).getVehicleType());
        holder.weight.setText(list.get(position).getWeight());
        holder.width.setText(list.get(position).getWidth());
        holder.height.setText(list.get(position).getHeight());
        holder.length.setText(list.get(position).getLength());
        holder.goods.setText(list.get(position).getGoodType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView truck_Image;
        TextView toReceiver, time, date, width, location, weight, height, length, vehicle,
                truck_name, goods;
        Button btn_share;
        public MyViewHolder(View itemView) {
            super(itemView);
            // set views
//            truck_name = itemView.findViewById(R.id.truck_name);
            truck_Image = itemView.findViewById(R.id.truck_Image);
            toReceiver = itemView.findViewById(R.id.to_receiver);
            date = itemView.findViewById(R.id.date_booked);
            location = itemView.findViewById(R.id.location_booked);
            time = itemView.findViewById(R.id.time_booked);
            vehicle = itemView.findViewById(R.id.truck_name);
            weight = itemView.findViewById(R.id.weight_recording);
            width = itemView.findViewById(R.id.width_recording);
            height = itemView.findViewById(R.id.height_recording);
            length = itemView.findViewById(R.id.length_recording);
            goods = itemView.findViewById(R.id.good_type);
            btn_share = itemView.findViewById(R.id.btn_share);
        }
    }
    public interface ItemClickListener {
        void onItemClick(DeliveryOrder list);
    }
}
