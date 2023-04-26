package com.example.agri_farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class crop_history extends AppCompatActivity {

    TextView startdate,duration,district,ph,firstheading,secondheading,thirdheading;
    ImageView firstimage,secondimage,thirdimage;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_history);
        startdate=findViewById(R.id.startdate);
        duration=findViewById(R.id.duration);
        district=findViewById(R.id.district);
        ph=findViewById(R.id.ph);
        firstheading=findViewById(R.id.firstheading);
        secondheading=findViewById(R.id.secondheading);
        thirdheading=findViewById(R.id.thirdheading);
        firstimage=findViewById(R.id.firstimage);
        secondimage=findViewById(R.id.secondimage);
        thirdimage=findViewById(R.id.thirdimage);
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.child(currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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

                    startdate.setText("Start Date: "+start_date1);
                    duration.setText("Duration: "+duration1);
                    district.setText("District: "+district1);
                    ph.setText("ph: "+ph1);
                    firstheading.setText(firstheading1);
                    secondheading.setText(secondheading1);
                    thirdheading.setText(thirdheading1);
                    if(firstimage1.equals("rice")){
                        firstimage.setImageResource(R.drawable.rice);
                    }
                    else if(firstimage1.equals("maize")){
                        firstimage.setImageResource(R.drawable.maize);
                    }
                    else if(firstimage1.equals("jowar")){
                        firstimage.setImageResource(R.drawable.jowar);
                    }
                    else if(firstimage1.equals("groundnut")){
                        firstimage.setImageResource(R.drawable.groundnut);
                    }
                    else if(firstimage1.equals("soyabean")){
                        firstimage.setImageResource(R.drawable.soyabean);
                    }
                    else if(firstimage1.equals("sugarcane")){
                        firstimage.setImageResource(R.drawable.sugarcane);
                    }
                    else if(firstimage1.equals("cotton")){
                        firstimage.setImageResource(R.drawable.cotton);
                    }
                    else if(firstimage1.equals("wheat")){
                        firstimage.setImageResource(R.drawable.wheat);
                    }
                    else if(firstimage1.equals("gram")){
                        firstimage.setImageResource(R.drawable.gram);
                    }
                    else {
                        firstimage.setImageResource(R.drawable.bajra);
                    }

                    if(secondimage1.equals("rice")){
                        secondimage.setImageResource(R.drawable.rice);
                    }
                    else if(secondimage1.equals("maize")){
                        secondimage.setImageResource(R.drawable.maize);
                    }
                    else if(secondimage1.equals("jowar")){
                        secondimage.setImageResource(R.drawable.jowar);
                    }
                    else if(secondimage1.equals("groundnut")){
                        secondimage.setImageResource(R.drawable.groundnut);
                    }
                    else if(secondimage1.equals("soyabean")){
                        secondimage.setImageResource(R.drawable.soyabean);
                    }
                    else if(secondimage1.equals("sugarcane")){
                        secondimage.setImageResource(R.drawable.sugarcane);
                    }
                    else if(secondimage1.equals("cotton")){
                        secondimage.setImageResource(R.drawable.cotton);
                    }
                    else if(secondimage1.equals("wheat")){
                        secondimage.setImageResource(R.drawable.wheat);
                    }
                    else if(secondimage1.equals("gram")){
                        secondimage.setImageResource(R.drawable.gram);
                    }
                    else {
                        secondimage.setImageResource(R.drawable.bajra);
                    }


                    if(thirdimage1.equals("rice")){
                        thirdimage.setImageResource(R.drawable.rice);
                    }
                    else if(thirdimage1.equals("maize")){
                        thirdimage.setImageResource(R.drawable.maize);
                    }
                    else if(thirdimage1.equals("jowar")){
                        thirdimage.setImageResource(R.drawable.jowar);
                    }
                    else if(thirdimage1.equals("groundnut")){
                        thirdimage.setImageResource(R.drawable.groundnut);
                    }
                    else if(thirdimage1.equals("soyabean")){
                        thirdimage.setImageResource(R.drawable.soyabean);
                    }
                    else if(thirdimage1.equals("sugarcane")){
                        thirdimage.setImageResource(R.drawable.sugarcane);
                    }
                    else if(thirdimage1.equals("cotton")){
                        thirdimage.setImageResource(R.drawable.cotton);
                    }
                    else if(thirdimage1.equals("wheat")){
                        thirdimage.setImageResource(R.drawable.wheat);
                    }
                    else if(thirdimage1.equals("gram")){
                        thirdimage.setImageResource(R.drawable.gram);
                    }
                    else {
                        thirdimage.setImageResource(R.drawable.bajra);
                    }
                    String checkdetails=firstheading.getText().toString();
                    if(checkdetails.equals("null")){
                        Toast.makeText(getApplicationContext(),"First Enter the Details",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Croppredict.class);
                        startActivity(intent);
                        finish();
                    }


                }
                else{
                    Toast.makeText(getApplicationContext(),"Can't Read the data",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}