package com.example.myfurnitureapp1.ui.profile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myfurnitureapp1.HomeActivity;
import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    CircleImageView profileImg;
    EditText name,email,number,address;
    Button update;

    DatabaseReference databaseReference;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Glide.with(getContext()).load(userModel.getProfileImg()).into(profileImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setAction(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Number = number.getText().toString();
                String Address = address.getText().toString();

                updateUserProfile(Name,Email,Number,Address);
            }
        });


        return root;
    }

    private void updateUserProfile( String Name,String Email, String Number, String Address) {

        if (TextUtils.isEmpty(Name)){
            Toast.makeText(getActivity(), "Name is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Number)){
            Toast.makeText(getActivity(), "Number is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Address)){
            Toast.makeText(getActivity(), "Address is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap User = new HashMap();

        User.put("name",Name);
        User.put("phone number",Number);
        User.put("address",Address);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        name.setText(userModel.getName());
                        email.setText(userModel.getEmail());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(FirebaseAuth.getInstance().getUid()).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){


                    name.setText("");
                    address.setText("");
                    number.setText("");

                    Toast.makeText(getActivity(),"Successfully Updated",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getActivity(),"Failed to Update",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData()!=null){
            Uri profileUri = data.getData();
            profileImg.setImageURI(profileUri);
            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();


                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded Sucessfully.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }

}