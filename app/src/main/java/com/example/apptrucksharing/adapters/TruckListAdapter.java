package com.example.apptrucksharing.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptrucksharing.R;
import com.example.apptrucksharing.activity.NewDeliveryActivity;
import com.example.apptrucksharing.model.Truck;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TruckListAdapter extends RecyclerView.Adapter<TruckListAdapter.TruckViewHolder>{

    List<Truck> truckList;
    private Context context;

    public TruckListAdapter(List<Truck> truckList, Context context) {
        this.truckList = truckList;
        this.context = context;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.truck_list, parent, false);
        return new TruckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder holder, int position) {
        Truck truck = truckList.get(position);
        holder.truck_description.setText(truck.getDescriptionTruck()); //description
        holder.truck_name.setText(truck.getTitleTruck()); //name
        holder.truck_available.setText("Available"); //name

        if(context!=null){
            if(position == 0)
            {
                Glide.with(context).load(R.drawable.van1).into(holder.truck_Image);
            }
            else if(position == 1)
            {
                Glide.with(context).load(R.drawable.van2).into(holder.truck_Image);
            }
            else if(position == 2)
            {
                Glide.with(context).load(R.drawable.van3).into(holder.truck_Image);
            }
            else if(position == 3)
            {
                Glide.with(context).load(R.drawable.van4).into(holder.truck_Image);
            }
            else
            {
                Glide.with(context).load(R.drawable.van1).into(holder.truck_Image);
            }
        }
        //Picasso.get().load(truckList.get(position).getTruckImage()).into(holder.truck_Image);
//        if(context!=null){
//            Glide.with(context).load(truckList.get(position).getImageDrawableResId()).into(holder.truck_Image);
//        }
    }

    @Override
    public int getItemCount() {
        return truckList == null ? 0 : truckList.size();
    }

    class TruckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView truck_Image;
        TextView truck_description, truck_name, truck_available;

        public TruckViewHolder(View itemView) {
            super(itemView);

            // set views
            truck_Image = itemView.findViewById(R.id.truck_Image);
            truck_description = itemView.findViewById(R.id.truck_description);
            truck_name = itemView.findViewById(R.id.truck_name);
            truck_available = itemView.findViewById(R.id.truck_available);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Truck truck = truckList.get(getAdapterPosition());
            Intent intent = new Intent(context, NewDeliveryActivity.class);
            intent.putExtra("truck", truck);
//            mCtx.startActivity(intent);
            Log.v("Button Pressed", "ShareButton Pressed");
            Toast.makeText(itemView.getContext(),"Share this!",Toast.LENGTH_LONG);
        }
    }

    public void setData(List<Truck> newData) {
        truckList = newData;
    }
}

