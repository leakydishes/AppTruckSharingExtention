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
    private Context context;

    public OrderListAdapter(List<DeliveryOrder> list, MyOrders clickListener, Context context) {
        this.list = list;
        this.clickListener = (ItemClickListener) clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_list, parent,false);
        return new MyViewHolder(view); //return view instance of the recycler
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, int position) {
        holder.receiver.setText(list.get(position).getReceiverName());
        holder.sender.setText(list.get(position).getSenderName());
        holder.date.setText(list.get(position).getDate());
        holder.pick_up_location.setText(list.get(position).getPickUpLocation());
        holder.drop_off_location.setText(list.get(position).getDropOffLocation());
        holder.pickTime.setText(list.get(position).getpickUpTime());
        holder.dropTime.setText(list.get(position).getdropoffTime());
        holder.vehicle.setText(list.get(position).getVehicleType());
        holder.weight.setText(list.get(position).getWeight());
        holder.width.setText(list.get(position).getWidth());
        holder.height.setText(list.get(position).getHeight());
        holder.length.setText(list.get(position).getLength());
        holder.goods.setText(list.get(position).getGoodType());

        String vehicleType = (list.get(position).getVehicleType());
        if(context!=null){
            if(vehicleType == "Van" || vehicleType == "Other")
            {
                Glide.with(context).load(R.drawable.van1).into(holder.truck_Image);
            }
            else if(vehicleType == "Truck")
            {
                Glide.with(context).load(R.drawable.van2).into(holder.truck_Image);
            }
            else if(vehicleType == "Mini-Truck")
            {
                Glide.with(context).load(R.drawable.van3).into(holder.truck_Image);
            }
            else if(vehicleType == "Refrigerated Truck")
            {
                Glide.with(context).load(R.drawable.van4).into(holder.truck_Image);
            }
        }

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
        TextView receiver, sender, pickTime, dropTime, date, width, weight, height,
                length, vehicle, goods, pick_up_location, drop_off_location;
        Button btn_share;
        public MyViewHolder(View itemView) {
            super(itemView);
            // set views
            truck_Image = itemView.findViewById(R.id.truck_Image);
            receiver = itemView.findViewById(R.id.receiver);
            sender = itemView.findViewById(R.id.sender);
            date = itemView.findViewById(R.id.date_booked);
            pick_up_location = itemView.findViewById(R.id.pick_up_location);
            drop_off_location = itemView.findViewById(R.id.drop_off_location);
            pickTime = itemView.findViewById(R.id.pick_up_time);
            dropTime = itemView.findViewById(R.id.drop_off_time);
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
