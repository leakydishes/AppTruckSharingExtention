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

import java.util.List;


public class TruckListAdapter extends RecyclerView.Adapter<TruckListAdapter.TruckViewHolder>{

    List<Truck> truckList;
    private Context mCtx;

    public TruckListAdapter(List<Truck> truckList, Context context) {
        this.truckList = truckList;
        this.mCtx = mCtx;
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
        Truck t = truckList.get(position);
        holder.truck_description.setText(t.getDescriptionTruck()); //description
        holder.truck_name.setText(t.getTitleTruck()); //name

        if(mCtx!=null){
            Glide.with(mCtx).load(truckList.get(position).getImageDrawableResId()).into(holder.truck_Image);
        }

        // Load the drawable image using Glide

        if (t.isFinished())
            holder.truck_description.setText("Not Available");
        else
            holder.truck_description.setText("Available");
    }

    @Override
    public int getItemCount() {
        return truckList == null ? 0 : truckList.size();
    }

    class TruckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView truck_Image;
        TextView truck_description, truck_name;

        public TruckViewHolder(View itemView) {
            super(itemView);

            // set views
            truck_Image = itemView.findViewById(R.id.truck_Image);
            truck_description = itemView.findViewById(R.id.truck_description);
            truck_name = itemView.findViewById(R.id.truck_name);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Truck truck = truckList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, NewDeliveryActivity.class);
            intent.putExtra("truck", truck);
//            mCtx.startActivity(intent);
            Log.v("Button Pressed", "ShareButton Pressed");
            Toast.makeText(itemView.getContext(),"Share this!",Toast.LENGTH_LONG);
        }
    }
    private void loadDrawableImage(ImageView imageView, int drawableResId) {
        Glide.with(mCtx)
                .load(drawableResId)
                .into(imageView);
    }

    public void setData(List<Truck> newData) {
        truckList = newData;
    }
}
