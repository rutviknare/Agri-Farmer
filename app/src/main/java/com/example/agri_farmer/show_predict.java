package com.example.agri_farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class show_predict extends AppCompatActivity {


    ImageView i1,i2,i3;
    TextView h1,h2,h3,l1,l2,l3,d1,d2,d3,s1,s2,s3,ph1,ph2,ph3;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_predict);
        i1=findViewById(R.id.firstimage);
        i2=findViewById(R.id.firstimage2);
        i3=findViewById(R.id.firstimage3);
        h1=findViewById(R.id.cropheading1);
        h2=findViewById(R.id.cropheading2);
        h3=findViewById(R.id.cropheading3);
        l1=findViewById(R.id.location1);
        l2=findViewById(R.id.location2);
        l3=findViewById(R.id.location3);
        d1=findViewById(R.id.duration1);
        d2=findViewById(R.id.duration2);
        d3=findViewById(R.id.duration3);
        s1=findViewById(R.id.startdate1);
        s2=findViewById(R.id.startdate2);
        s3=findViewById(R.id.startdate3);
        ph1=findViewById(R.id.phvalue1);
        ph2=findViewById(R.id.phvalue2);
        ph3=findViewById(R.id.phvalue3);

        Intent intent=getIntent();
        String startdate=intent.getStringExtra("startdate");
        String duration=intent.getStringExtra("duration");
        String district=intent.getStringExtra("district");
        String ph=intent.getStringExtra("ph");
        String username=intent.getStringExtra("username");
        String mobile=intent.getStringExtra("mobile");
        String date=intent.getStringExtra("date");


        mAuth= FirebaseAuth.getInstance();
        int duration_int=Integer.parseInt(duration);
        int ph_int=Integer.parseInt(ph);
        if(district.equals("Nashik")){
            if(duration_int>=0 && duration_int<=4 && ph_int>=3 && ph_int<=5){
                      i1.setImageResource(R.drawable.rice);
                      h1.setText("Rice");
                      i2.setImageResource(R.drawable.maize);
                      h2.setText("Maize");
                      i3.setImageResource(R.drawable.jowar);
                      h3.setText("Jowar");

                      s1.setText("Start Date: "+startdate);
                      d1.setText("Duration: "+duration);
                      ph1.setText("Soil pH Value: "+ph);
                      l1.setText("Location: "+district);

                      String firstheading=h1.getText().toString();
                      String secondheading=h2.getText().toString();
                      String thirdheading=h3.getText().toString();
                      String firstimage=firstheading.toLowerCase();
                      String secondimage=secondheading.toLowerCase();
                      String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading,username,mobile,date);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else if(duration_int>4 && duration_int<=8 && ph_int>=6 && ph_int<=8){
                i1.setImageResource(R.drawable.ragi);
                h1.setText("Ragi");
                i2.setImageResource(R.drawable.groundnut);
                h2.setText("Groundnut");
                i3.setImageResource(R.drawable.soyabean);
                h3.setText("Soyabean");

                s2.setText("Start Date: "+startdate);
                d2.setText("Duration: "+duration);
                ph2.setText("Soil pH Value: "+ph);
                l2.setText("Location: "+district);

                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                i1.setImageResource(R.drawable.sugarcane);
                h1.setText("Sugarcane");
                i2.setImageResource(R.drawable.cotton);
                h2.setText("Cotton");
                i3.setImageResource(R.drawable.wheat);
                h3.setText("Wheat");

                s3.setText("Start Date: "+startdate);
                d3.setText("Duration: "+duration);
                ph3.setText("Soil pH Value: "+ph);
                l3.setText("Location: "+district);

                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        else if(district.equals("Sangli")){

            if(duration_int>=0 && duration_int<=4 && ph_int>=3 && ph_int<=5){

                i1.setImageResource(R.drawable.ragi);
                h1.setText("Ragi");
                i2.setImageResource(R.drawable.cotton);
                h2.setText("Cotton");
                i3.setImageResource(R.drawable.jowar);
                h3.setText("Jowar");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else if(duration_int>4 && duration_int<=8 && ph_int>=6 && ph_int<=8){

                i1.setImageResource(R.drawable.rice);
                h1.setText("Rice");
                i2.setImageResource(R.drawable.groundnut);
                h2.setText("Groundnut");

                i3.setImageResource(R.drawable.wheat);
                h3.setText("Wheat");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                i1.setImageResource(R.drawable.sugarcane);
                h1.setText("Sugarcane");
                i2.setImageResource(R.drawable.maize);
                h2.setText("Maize");
                i3.setImageResource(R.drawable.soyabean);
                h3.setText("Soyabean");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });

            }


        }
        else if(district.equals("Kolhapur")){

            if(duration_int>=0 && duration_int<=4 && ph_int>=3 && ph_int<=5){

                i1.setImageResource(R.drawable.ragi);
                h1.setText("Ragi");
                i2.setImageResource(R.drawable.groundnut);
                h2.setText("Groundnut");
                i3.setImageResource(R.drawable.soyabean);
                h3.setText("Soyabean");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else if(duration_int>4 && duration_int<=8 && ph_int>=6 && ph_int<=8){
                i1.setImageResource(R.drawable.rice);
                h1.setText("Rice");
                i2.setImageResource(R.drawable.maize);
                h2.setText("Maize");
                i3.setImageResource(R.drawable.jowar);
                h3.setText("Jowar");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                i1.setImageResource(R.drawable.sugarcane);
                h1.setText("Sugarcane");
                i2.setImageResource(R.drawable.cotton);
                h2.setText("Cotton");
                i3.setImageResource(R.drawable.wheat);
                h3.setText("Wheat");
                String firstheading=h1.getText().toString();
                String secondheading=h2.getText().toString();
                String thirdheading=h3.getText().toString();
                String firstimage=firstheading.toLowerCase();
                String secondimage=secondheading.toLowerCase();
                String thirdimage=thirdheading.toLowerCase();

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                Cropdata cropdata=new Cropdata(startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading);
                db=FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        String firstheading=h1.getText().toString();
        String secondheading=h2.getText().toString();
        String thirdheading=h3.getText().toString();
        Intent intent1=new Intent(getApplicationContext(),userprofile.class);
        intent1.putExtra("startdate",startdate);
        intent1.putExtra("duration",duration);
        intent1.putExtra("district",district);
        intent1.putExtra("ph",ph);
        intent1.putExtra("firstheading",h1.getText().toString());
        intent1.putExtra("secondheading",h2.getText().toString());
        intent1.putExtra("thirdheading",h3.getText().toString());
        intent1.putExtra("firstimage",firstheading.toLowerCase());
        intent1.putExtra("secondimage",secondheading.toLowerCase());
        intent1.putExtra("thirdimage",thirdheading.toLowerCase());
//        startActivity(intent1);
//        finish();

    }
}