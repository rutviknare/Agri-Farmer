package com.example.agri_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Croppredict extends AppCompatActivity {

    EditText d1;
    Calendar calendar;
    String[] items={"1","2","3","4","5","6","7","8","9","10","11","12"};
    ArrayAdapter<String>  adapterItems;
    Button fetchingweather,predict_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croppredict);
        d1=findViewById(R.id.selectdate);


        fetchingweather=(Button)findViewById(R.id.fetching_weather);
        final LoadingDialog loadingDialog=new LoadingDialog(Croppredict.this);
        fetchingweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        fetchingweather.setText("Fetch Successfully");
                        fetchingweather.setBackgroundColor(Color.GREEN);
                    }
                },3000);
            }
        });


        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR,year);
                calendar.set(calendar.MONTH,month);
                calendar.set(calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private void updateCalender(){
                String Format="MM/dd/yy";
                SimpleDateFormat sdf=new SimpleDateFormat(Format, Locale.US);
                d1.setText(sdf.format(calendar.getTime()));
            }
        };
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Croppredict.this,date,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        predict_btn=findViewById(R.id.btn_predict);
        predict_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingDialog.startLoadingDialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        Intent intent=new Intent(Croppredict.this,show_predict.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
        });
    }
}