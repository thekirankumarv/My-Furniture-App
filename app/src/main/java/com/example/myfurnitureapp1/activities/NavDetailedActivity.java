package com.example.myfurnitureapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class NavDetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView navdetailedimg;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    NavCategoryDetailedModel navCategoryDetailedModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore  = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("navdetail");
        if(object instanceof NavCategoryDetailedModel){
            navCategoryDetailedModel = (NavCategoryDetailedModel) object;
        }

        quantity = findViewById(R.id.quantity);

        navdetailedimg = findViewById(R.id.nav_detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        price = findViewById(R.id.nav_detailed_price);
        rating = findViewById(R.id.nav_detailed_rating);
        description = findViewById(R.id.nav_detailed_dec);


        if(navCategoryDetailedModel != null){
            Glide.with(getApplicationContext()).load(navCategoryDetailedModel.getImg_url()).into(navdetailedimg);
            rating.setText(navCategoryDetailedModel.getRating());
            description.setText(navCategoryDetailedModel.getDescription());

            price.setText("Price :Rs"+navCategoryDetailedModel.getPrice()+" ");

            totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;

        }



        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
                }

            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;

                }
            }
        });
    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",navCategoryDetailedModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(NavDetailedActivity.this, "Added To a Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    }
