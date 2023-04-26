package com.example.agri_farmer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class userprofile extends AppCompatActivity {


    TextView fullname,mobileno,accweather,recentcrop,help,about;
    LinearLayout logout;
    ImageView imgprofile;
    ImageView editbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        fullname = findViewById(R.id.acc_full_name);
        mobileno = findViewById(R.id.acc_phone);
        accweather = findViewById(R.id.acc_weather_tv);
        recentcrop = findViewById(R.id.acc_crop_history_tv);
        help = findViewById(R.id.acc_help_tv);
        about = findViewById(R.id.acc_about_us_tv);
        logout = findViewById(R.id.acc_ll_4);
        editbutton = findViewById(R.id.acc_edit);
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
                Toast.makeText(getApplicationContext(), "Updated Soon....", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

