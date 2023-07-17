package com.example.myfurnitureapp1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.activities.PlaceOrderActivity;
import com.example.myfurnitureapp1.adapters.NavCategoryDetailedAdapter;
import com.example.myfurnitureapp1.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    TextView quantity;
    int  totalQuantity =1;
    int totalPrice=0;

    NavCategoryDetailedModel navCategoryDetailedModel=null;

    RecyclerView recyclerView;
    NavCategoryDetailedAdapter adapter;
    List<NavCategoryDetailedModel> list;
    FirebaseFirestore db;
    ProgressBar progressBar;

    ImageView addItem,removeItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String type = getIntent().getStringExtra("type");


        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        adapter=new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);

        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        quantity=findViewById(R.id.quantity);


        if(type != null && type.equalsIgnoreCase("bed"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","bed").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("sofa"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","sofa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("tvunits"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","tvunits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("coffeetable"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","coffeetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("dressingtable"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","dressingtable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("chair"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","chair").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        if(type != null && type.equalsIgnoreCase("wardrobe"))
        {
            db.collection("NavCategoryDetailed").whereEqualTo("type","wardrobe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }


}