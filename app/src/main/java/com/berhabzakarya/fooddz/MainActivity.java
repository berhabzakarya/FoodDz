package com.berhabzakarya.fooddz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.berhabzakarya.fooddz.Main.SignInActivity;
import com.berhabzakarya.fooddz.Main.SignUpActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSignIn,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize object button
        initViews();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start sign up activity
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start sign in activity
                startActivity(new Intent(MainActivity.this,SignInActivity.class));
            }
        });
    }
    private void initViews(){
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
