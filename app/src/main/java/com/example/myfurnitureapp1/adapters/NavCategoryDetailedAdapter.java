package com.example.myfurnitureapp1.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.activities.DetailedActivity;
import com.example.myfurnitureapp1.activities.NavDetailedActivity;
import com.example.myfurnitureapp1.activities.ViewAllActivity;
import com.example.myfurnitureapp1.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailedModel> list;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice()+" ");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavDetailedActivity.class);
                intent.putExtra("navdetail", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,description,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.navdet_img);
            name = itemView.findViewById(R.id.navdet_name);
            price = itemView.findViewById(R.id.navdet_price);
            description = itemView.findViewById(R.id.navdet_dec);
            rating = itemView.findViewById(R.id.navdet_rating);




        }
    }
}
