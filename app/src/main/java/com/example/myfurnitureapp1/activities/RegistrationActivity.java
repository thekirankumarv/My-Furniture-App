package com.example.myfurnitureapp1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfurnitureapp1.R;
import com.example.myfurnitureapp1.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    EditText name,email,password,phone;
    TextView signIn;

    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        name = findViewById(R.id.name);
        signIn = findViewById(R.id.sign_in);
        email=findViewById(R.id.email_reg);
        password = findViewById(R.id.password_reg);
        signUp = findViewById(R.id.login_btn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        //String userPhone= phone.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() < 6){
            Toast.makeText(this, "Password length must be greater than 6 letters", Toast.LENGTH_SHORT).show();
            return;
        }

        //Create User
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, "Registration Successful!! Please verify with Your Email", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(RegistrationActivity.this, "Not Registered", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            UserModel userModel = new UserModel(userName,userEmail,userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);
                            progressBar.setVisibility(View.GONE);

                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "User already exits"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}