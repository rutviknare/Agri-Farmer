package com.example.agri_farmer;

import androidx.annotation.NonNull;
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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agri_farmer.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Croppredict extends AppCompatActivity {

    EditText d1,durations,phs;
    Calendar calendar;
    Spinner districts;
    FirebaseAuth mAuth;

//    String[] items={"1","2","3","4","5","6","7","8","9","10","11","12"};
//    ArrayAdapter<String>  adapterItems;

    String startdate,duration,district,ph;
    FirebaseDatabase db;
    DatabaseReference reference;

    Button fetchingweather,predict_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croppredict);
        d1=findViewById(R.id.selectdate);
        durations=findViewById(R.id.duration);
        districts=findViewById(R.id.district);
        phs=findViewById(R.id.ph);
        mAuth=FirebaseAuth.getInstance();

        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(Croppredict.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.district));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districts.setAdapter(myadapter);


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
                String Format="dd MMMM yyyy";
//                MM/dd/yy
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
                        startdate=d1.getText().toString();
                        duration=durations.getText().toString();
                        district=districts.getSelectedItem().toString();
                        ph=phs.getText().toString();

                        if(!startdate.isEmpty() && !duration.isEmpty() && !district.isEmpty() && !ph.isEmpty()){
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            Cropdata cropdata=new Cropdata(startdate,duration,district,ph);
                            db=FirebaseDatabase.getInstance();
                            reference=db.getReference("Users");
                            reference.child(currentFirebaseUser.getUid()).setValue(cropdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    d1.setText("");
                                    durations.setText("");
                                    phs.setText("");
                                    Toast.makeText(getApplicationContext(),"successfully updated",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Croppredict.this,show_predict.class);
                                    intent.putExtra("startdate",startdate);
                                    intent.putExtra("duration",duration);
                                    intent.putExtra("district",district);
                                    intent.putExtra("ph",ph);
                                   startActivity(intent);
                                     finish();
                                }
                            });
                        }

                    }
                },3000);
            }
        });
    }
}