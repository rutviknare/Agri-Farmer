package com.example.agri_farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoard extends AppCompatActivity {

    CardView croppredict,logout,weather,proj_video,recenthistory;
    TextView dashboard;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        croppredict=findViewById(R.id.crop_predict);
        logout=findViewById(R.id.log_out);
        weather=findViewById(R.id.weath_fore);
        proj_video=findViewById(R.id.projectvideo);
        recenthistory=findViewById(R.id.recenthistory);
//        history=findViewById(R.id.crophistory);
        dashboard=findViewById(R.id.dashboard);
        mAuth=FirebaseAuth.getInstance();
        croppredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),Croppredict.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),userprofile.class);
                startActivity(intent);

            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),weather_forecasting.class);
                startActivity(intent);
            }
        });

        proj_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),projectvideo.class);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(),"Video will Uploading Soon",Toast.LENGTH_SHORT).show();
            }
        });
        recenthistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),crop_history.class);
                startActivity(intent);
            }
        });
//        history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplication(),crop_history.class);
//                startActivity(intent);
//            }
//        });
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        dashboard.setText(currentFirebaseUser.getEmail());
    }
}