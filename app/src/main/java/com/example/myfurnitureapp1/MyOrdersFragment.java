package com.example.myfurnitureapp1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfurnitureapp1.adapters.MyOrderAdapter;
import com.example.myfurnitureapp1.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    ProgressBar progressBar;

    RecyclerView recyclerView;
    MyOrderAdapter ordersAdapter;
    List<MyOrdersModel> ordersModelList;



    public MyOrdersFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_my_orders, container, false);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        progressBar=root.findViewById(R.id.progressbar1);
        progressBar.setVisibility(View.GONE);

        recyclerView = root.findViewById(R.id.recyclerview1);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ordersModelList = new ArrayList<>();
        ordersAdapter = new MyOrderAdapter(getActivity(), ordersModelList);

        recyclerView.setAdapter(ordersAdapter);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String documentId = documentSnapshot.getId();

                        MyOrdersModel ordersModel = documentSnapshot.toObject(MyOrdersModel.class);
                        ordersModel.setDocumentId(documentId);

                        ordersModelList.add(ordersModel);
                        ordersAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                }
            }
        });





        return root;
    }


}