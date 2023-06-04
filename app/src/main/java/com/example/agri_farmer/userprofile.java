package com.example.agri_farmer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class userprofile extends AppCompatActivity {


    TextView fullname,mobileno1,DOB,email,accweather,recentcrop,help,about;
    LinearLayout logout;
    ImageView imgprofile;
    ImageView editbutton;
    Dialog mDialog;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;
    String name,mobileno,birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        fullname = findViewById(R.id.acc_full_name);
        mobileno1 = findViewById(R.id.acc_phone);
        DOB=findViewById(R.id.birthdisplay);
        email=findViewById(R.id.acc_email);
        accweather = findViewById(R.id.acc_weather_tv);
        recentcrop = findViewById(R.id.acc_crop_history_tv);
        help = findViewById(R.id.acc_help_tv);
        about = findViewById(R.id.acc_about_us_tv);
        logout = findViewById(R.id.acc_ll_4);
        editbutton = findViewById(R.id.acc_edit);
        mDialog=new Dialog(this);

        Intent intent=getIntent();
        String startdate=intent.getStringExtra("startdate");
        String duration=intent.getStringExtra("duration");
        String district=intent.getStringExtra("district");
        String ph=intent.getStringExtra("ph");
        String firstheading=intent.getStringExtra("firstheading");
        String secondheading=intent.getStringExtra("secondheading");
        String thirdheading=intent.getStringExtra("thirdheading");
        String firstimage=intent.getStringExtra("firstimage");
        String secondimage=intent.getStringExtra("secondimage");
        String thirdimage=intent.getStringExtra("thirdimage");


        accweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), weather_forecasting.class);
                startActivity(intent);
            }
        });
        recentcrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), crop_history.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Updated Soon....", Toast.LENGTH_SHORT).show();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Updated Soon....", Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(R.layout.activity_editprofile);
                mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                mDialog.setCancelable(false);
                mDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                final EditText username1=mDialog.findViewById(R.id.username);
                final EditText mobile1=mDialog.findViewById(R.id.number);
                final EditText DOB1=mDialog.findViewById(R.id.birth);
                Button update=mDialog.findViewById(R.id.update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        String username=username1.getText().toString();
                        String mobile=mobile1.getText().toString();
                        String date=DOB1.getText().toString();
                        String email1=currentFirebaseUser.getEmail();
                        Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading,username,mobile,date);
                        db=FirebaseDatabase.getInstance();
                        reference=db.getReference("Users");
                        reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                username1.setText("");
                                mobile1.setText("");
                                DOB1.setText("");
//                                fullname.setText("Name: "+username);
//                                mobileno1.setText("Mobile No: "+mobile);
//                                DOB.setText("Date Of Birth: "+date);
//                                email.setText("Email Id: "+email1);

                                Intent intent1=new Intent(getApplicationContext(),show_predict.class);
                                intent1.putExtra("username",username);
                                intent1.putExtra("mobile",mobile);
                                intent1.putExtra("date",date);
                                Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                mDialog.show();

//                Intent intent = new Intent(getApplication(), editprofile.class);
//                startActivity(intent);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), aboutus.class);
                startActivity(intent);
            }
        });

//        Intent intent=getIntent();
//        String username=intent.getStringExtra("username");
//        String mobileno=intent.getStringExtra("mobileno");
//        String DOB1=intent.getStringExtra("DOB");
        mAuth= FirebaseAuth.getInstance();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        Cropdata cropdata=new Cropdata(username,mobileno,DOB1);
        db=FirebaseDatabase.getInstance();
        reference=db.getReference("Users");
        reference.child(currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()  {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot dataSnapshot=task.getResult();
                    String start_date1=String.valueOf(dataSnapshot.child("startdate").getValue());
                    String duration1=String.valueOf(dataSnapshot.child("duration").getValue());
                    String district1=String.valueOf(dataSnapshot.child("district").getValue());
                    String ph1=String.valueOf(dataSnapshot.child("ph").getValue());
                    String firstimage1=String.valueOf(dataSnapshot.child("firstimage").getValue());
                    String secondimage1=String.valueOf(dataSnapshot.child("secondimage").getValue());
                    String thirdimage1=String.valueOf(dataSnapshot.child("thirdimage").getValue());
                    String firstheading1=String.valueOf(dataSnapshot.child("firstheading").getValue());
                    String secondheading1=String.valueOf(dataSnapshot.child("secondheading").getValue());
                    String thirdheading1=String.valueOf(dataSnapshot.child("thirdheading").getValue());
                    String username=String.valueOf(dataSnapshot.child("username").getValue());
                    String mobile=String.valueOf(dataSnapshot.child("mobilenumber").getValue());
                    String DOB1=String.valueOf(dataSnapshot.child("dob").getValue());

                    if(!username.equals("null")){
                        fullname.setText("Name: "+username);
                        mobileno1.setText("Mobile No: "+mobile);
                        DOB.setText("Date Of Birth: "+DOB1);
                        email.setText("Email Id: "+currentFirebaseUser.getEmail());
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Can't Read the data",Toast.LENGTH_SHORT).show();
                }
            }
        });







    }

}

