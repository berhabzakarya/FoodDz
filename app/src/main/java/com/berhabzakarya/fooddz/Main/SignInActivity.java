package com.berhabzakarya.fooddz.Main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.berhabzakarya.fooddz.Model.Users;
import com.berhabzakarya.fooddz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private MaterialEditText edtPhone, edtPassword;
    private Button btnSignIn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    Toast.makeText(SignInActivity.this, "Please write phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    Toast.makeText(SignInActivity.this, "Please write password", Toast.LENGTH_SHORT).show();
                } else {
                    signIn();
                }
            }
        });
    }

    private void signIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please waiting ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        databaseReference.child(edtPhone.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Users user = dataSnapshot.child(edtPhone.getText().toString()).getValue(Users.class);
                    if (edtPassword.getText().toString().equals(user.getPassword())) {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "This phone number is not registered here.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initViews() {
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    }
}
