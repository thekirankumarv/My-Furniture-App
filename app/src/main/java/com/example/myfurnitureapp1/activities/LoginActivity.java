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

import com.example.myfurnitureapp1.HomeActivity;
import com.example.myfurnitureapp1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText email,password;
    TextView signUp;

    FirebaseAuth auth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signIn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loginUser();

            }
        });
    }

    private void loginUser() {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() < 6){
            Toast.makeText(this, "Password length must be greater than 6 letters", Toast.LENGTH_SHORT).show();
            return;
        }

        //Login User
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (auth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Please Verify your Email to Login",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }


                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Username and Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}