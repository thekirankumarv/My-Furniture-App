package com.example.myfurnitureapp1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.models.MyOrdersModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {


    FirebaseFirestore fs;
    FirebaseAuth auth;

    Context context;
    List<MyOrdersModel> ordersModelList;


    public MyOrderAdapter(Context context, List<MyOrdersModel> ordersModelList) {
        this.fs = fs;
        this.auth = auth;
        this.context = context;
        this.ordersModelList = ordersModelList;
    }

    @NonNull
    @NotNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyOrderAdapter.ViewHolder holder, int position) {

        holder.name.setText(ordersModelList.get(position).getProductName());
        holder.price.setText(ordersModelList.get(position).getProductPrice());
        holder.date.setText(ordersModelList.get(position).getCurrentDate());
        holder.time.setText(ordersModelList.get(position).getCurrentTime());
        holder.totalPrice.setText(String.valueOf(ordersModelList.get(position).getTotalPrice()));
        holder.quantity.setText(ordersModelList.get(position).getTotalQuantity());
    }

    @Override
    public int getItemCount() {
        return ordersModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quantity,totalPrice;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            quantity=itemView.findViewById(R.id.total_quantity);
            totalPrice=itemView.findViewById(R.id.total_price);

        }
    }
}
