package com.example.agri_farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class editprofile extends AppCompatActivity {

    EditText username,mobilenumber,birth;
    Button updatebutton;
    String name,mobileno,DOB;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        username=findViewById(R.id.username);
        mobilenumber=findViewById(R.id.number);
        birth=findViewById(R.id.birth);
        updatebutton=findViewById(R.id.update);
        mAuth= FirebaseAuth.getInstance();
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showprofile();
            }


        });
    }
    private void showprofile() {
        Toast.makeText(getApplicationContext(),"Hello user",Toast.LENGTH_SHORT).show();
    }
}