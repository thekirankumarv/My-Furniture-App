package com.example.myfurnitureapp1.adapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.activities.NavCategoryActivity;
import com.example.myfurnitureapp1.models.NavCategoryModel;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class
    NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {
    Context context;
    List<NavCategoryModel> list;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.discount.setText(list.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type",list.get(position).getType());
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
        TextView name,description,discount;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.cat_nav_img);
            name=itemView.findViewById(R.id.nav_cat_name);
            description=itemView.findViewById(R.id.nav_cat_description);
            discount=itemView.findViewById(R.id.nav_cat_discount);
        }


    }
}
