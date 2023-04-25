package com.example.agri_farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class weather_forecasting extends AppCompatActivity {

    TextView temp, location1, windspeed,precip,humidity,lag_log,wcdecs,date;
    Button title;
    ImageView weather;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    private String weatherurl="https://weather-api-n7w1.onrender.com/currentWeather";
    RequestQueue requestQueue;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecasting);
        temp=findViewById(R.id.wc_temperature);
        location1=findViewById(R.id.wc_location);
        windspeed=findViewById(R.id.wc_windspeed);
        precip=findViewById(R.id.wc_precip);
        humidity=findViewById(R.id.wc_humidity);
        lag_log=findViewById(R.id.wc_lat_long);
        title=findViewById(R.id.wc_title);
        wcdecs=findViewById(R.id.wc_description);
        date=findViewById(R.id.wc_day_date);
        weather=findViewById(R.id.weatherimage);
        mAuth=FirebaseAuth.getInstance();
        final LoadingDialog loadingDialog=new LoadingDialog(weather_forecasting.this);


        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                loadingDialog.startLoadingDialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        reference= FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(currentFirebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if(task.isSuccessful()){

//                                    Toast.makeText(weather_forecasting.this,"Successfully Read",Toast.LENGTH_LONG).show();
                                    DataSnapshot dataSnapshot=task.getResult();
                                    String temp_location=String.valueOf(dataSnapshot.child("district").getValue());
                                    String location=temp_location.toLowerCase();
                                    String hour=String.valueOf(dataSnapshot.child("duration").getValue());
                                    String start_date=String.valueOf(dataSnapshot.child("startdate").getValue());
                                    date.setText(start_date);

                                    StringRequest stringRequest=new StringRequest(Request.Method.POST,weatherurl,response ->{
                                        try{
                                            JSONObject jsonObject=new JSONObject(response);
                                            temp.setText(jsonObject.getString("temp"));
                                            location1.setText(jsonObject.getString("address"));
                                            windspeed.setText(jsonObject.getString("windspeed"));
                                            precip.setText(jsonObject.getString("precip"));
                                            humidity.setText(jsonObject.getString("humidity"));
                                            wcdecs.setText(jsonObject.getString("description"));
                                            lag_log.setText("LAT: "+jsonObject.getString("latitude")+" & LONG: "+jsonObject.getString("longitude"));
                                            weather.setImageResource(R.drawable.ic_weather_forecasting);
                                            Toast.makeText(weather_forecasting.this,"Data Successfully Fetch",Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    },
                                            error -> Toast.makeText(weather_forecasting.this,"Please Click the Button Again",Toast.LENGTH_LONG).show()){
                                        //Add parameter to the request
                                        @Override
                                        protected Map<String,String> getParams() throws AuthFailureError {
                                            Map<String,String> params=new HashMap<>();
                                            params.put("location",location);
                                            params.put("hour",hour);
                                            return params;
                                        }
                                    };
                                    requestQueue= Volley.newRequestQueue(weather_forecasting.this);
                                    requestQueue.add(stringRequest);

                                }else {
                                    Toast.makeText(getApplicationContext(),"Failed to read",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                },3000);

            }
        });


    }
}