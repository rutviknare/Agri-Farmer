package com.example.agri_farmer;

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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

    TextView temp, location1, windspeed,precip,humidity,lag_log,wcdecs;
    Button title;
    private String weatherurl="https://weather-api-n7w1.onrender.com/currentWeather";
    RequestQueue requestQueue;
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
                        String location="nashik";
                        String hour="12";

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
                                Toast.makeText(weather_forecasting.this,"success ",Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                                error -> Toast.makeText(weather_forecasting.this,"failure",Toast.LENGTH_LONG).show()){
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

                    }
                },3000);

            }
        });


    }
}