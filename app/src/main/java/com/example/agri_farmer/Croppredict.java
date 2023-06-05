package com.example.agri_farmer;

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
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.agrifarmer.R;
import com.example.agrifarmer.getterSetterClasses.CropRecord;
import com.example.agrifarmer.localDatabases.CropRecordDbHandler;
import com.example.agrifarmer.localDatabases.MyProfileDbHandler;
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

public class Croppredict extends AppCompatActivity {
    private TextInputLayout startDate, duration, soilPH;
    private TextInputEditText editStartDate, editSoilPH;
    private AutoCompleteTextView autoDuration;
    private TextView startFetch, startPrediction;
    private LottieAnimationView fetchAnimation;
    Dialog dialog;
    ArrayList<String> crops = new ArrayList<>();
    private String urlPredictCrop = "https://weather-api-n7w1.onrender.com/predictCrop";
    private MyProfileDbHandler profileDbHandler = new MyProfileDbHandler(PredictCropsActivity.this);
    private CropRecordDbHandler recordDbHandler = new CropRecordDbHandler(PredictCropsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_crops);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setUIViews();
        HashMap<String, String> months = new HashMap<>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
        String[] duration_in_months = getResources().getStringArray(R.array.duration_in_months);
        ArrayAdapter<String> durationAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item,
                duration_in_months);
        autoDuration.setAdapter(durationAdapter);
        MaterialDatePicker.Builder<Long> dateBuilder = MaterialDatePicker.Builder.datePicker();
        dateBuilder.setTitleText("Select Start Date");
        final MaterialDatePicker materialDatePicker = dateBuilder.build();
        editStartDate.setOnClickListener(
                view -> materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER"));
        materialDatePicker.addOnPositiveButtonClickListener(
                selection -> editStartDate.setText(materialDatePicker.getHeaderText()));
        /*
         * startDate.getEditText().addTextChangedListener(new TextWatcher() {
         * 
         * @Override
         * public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
         * i2) {}
         * 
         * @Override
         * public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
         * {}
         * 
         * @Override
         * public void afterTextChanged(Editable editable) {
         * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         * LocalDateTime now = LocalDateTime.now();
         * String today = dtf.format(now);
         * SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
         * try {
         * Date d1 = format.parse(today);
         * Date d2 = format.parse(startDate.getEditText().getText().toString());
         * if (d1.compareTo(d2) > 0)
         * startDate.setError("Please select future date!!");
         * else
         * startDate.setError(null);
         * } catch (ParseException e) {
         * e.printStackTrace();
         * }
         * }
         * });
         */
        autoDuration.setOnItemClickListener((adapterView, view, i, l) -> duration.setError(null));
        startFetch.setOnClickListener(View -> {
            String startingDate = startDate.getEditText().getText().toString();
            String strDuration = duration.getEditText().getText().toString();
            if (startingDate.equals("") || strDuration.equals("")) {
                if (startingDate.equals(""))
                    startDate.setError("Empty Credential!");
                if (strDuration.equals(""))
                    duration.setError("Empty Credential!");
            } else {
                if (startDate.getError() == null && duration.getError() == null) {
                    startFetch.setVisibility(android.view.View.GONE);
                    fetchAnimation.setVisibility(android.view.View.VISIBLE);
                    fetchAnimation.playAnimation();
                } else {
                    Toast.makeText(this, "Please enter valid credentials!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        soilPH.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ph = soilPH.getEditText().getText().toString();
                if (ph.equals("")) {
                    soilPH.setError("Empty Credential!");
                } else {
                    float fph = Float.parseFloat(ph);
                    if (fph < 4.0f || fph > 12.0)
                        soilPH.setError("Please enter valid value!");
                    else
                        soilPH.setError(null);
                }
            }
        });
        startPrediction.setOnClickListener(View -> {
            if (startFetch.getVisibility() == android.view.View.GONE) {
                String[] date = startDate.getEditText().getText().toString().split(" ");
                String finalDate = date[1].substring(0, date[1].length() - 1) + "-" + months.get(date[0]);
                String finalDuration = autoDuration.getText().toString();
                String finalPH = soilPH.getEditText().getText().toString();
                String finalLocation = profileDbHandler.getRegisteredUser().getLocation().toLowerCase(Locale.ROOT);
                dialog = new Dialog(PredictCropsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.message_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                LottieAnimationView animationView = dialog.findViewById(R.id.message_lottie_animation);
                TextView message = dialog.findViewById(R.id.message_textview);
                Button positiveBtn = dialog.findViewById(R.id.message_positive_btn);
                Button negativeBtn = dialog.findViewById(R.id.message_negative_btn);
                animationView.setAnimation(R.raw.corn_growing_animation);
                message.setText(R.string.processing);
                positiveBtn.setVisibility(android.view.View.GONE);
                negativeBtn.setVisibility(android.view.View.GONE);
                CountDownTimer timer = new CountDownTimer(1000, 100) {
                    @Override
                    public void onTick(long l) {
                    }

                    @Override
                    public void onFinish() {
                        animationView.setAnimation(R.raw.farmer_animation);
                        message.setText(R.string.process_complete);
                        positiveBtn.setText(R.string.show_results);
                        positiveBtn.setVisibility(android.view.View.VISIBLE);
                        positiveBtn.setOnClickListener(view -> {
                            dialog.dismiss();
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            CropRecord cropRecord = new CropRecord();
                            cropRecord.setCrop1(crops.get(0));
                            cropRecord.setCrop2(crops.get(1));
                            cropRecord.setCrop3(crops.get(2));
                            cropRecord.setLocation(profileDbHandler.getRegisteredUser().getLocation());
                            cropRecord.setDuration(finalDuration);
                            cropRecord.setDatetime(String.valueOf(dtf.format(now)));
                            recordDbHandler.addRecord(cropRecord);
                            Intent intent = new Intent(PredictCropsActivity.this, ShowResultActivity.class);
                            intent.putExtra("location", profileDbHandler.getRegisteredUser().getLocation());
                            intent.putExtra("crop1", crops.get(0));
                            intent.putExtra("crop2", crops.get(1));
                            intent.putExtra("crop3", crops.get(2));
                            intent.putExtra("duration", finalDuration);
                            startActivity(intent);
                            finish();
                        });
                    }
                };
                StringRequest request = new StringRequest(Request.Method.POST, urlPredictCrop,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray array = jsonObject.getJSONArray("Suitable Crop");
                                for (int i = 0; i < array.length(); i++) {
                                    crops.add(String.valueOf(array.get(i)));
                                }
                                timer.start();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {
                            animationView.setAnimation(R.raw.failed_animation);
                            animationView.playAnimation();
                            message.setText(R.string.network_error);
                            positiveBtn.setText(R.string.ok);
                            positiveBtn.setVisibility(android.view.View.VISIBLE);
                            positiveBtn.setOnClickListener(view -> dialog.dismiss());
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("ph", finalPH);
                        params.put("startDate", finalDate);
                        params.put("duration", finalDuration);
                        params.put("location", finalLocation);
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(PredictCropsActivity.this);
                dialog.show();
                queue.add(request);
            } else {
                Toast.makeText(this, "Please get weather data first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUIViews() {
        startDate = findViewById(R.id.predict_start_date_textInputLayout);
        duration = findViewById(R.id.predict_duration_textInputLayout);
        soilPH = findViewById(R.id.predict_soilPH_textInputLayout);
        editStartDate = findViewById(R.id.predict_start_date_textInputEditText);
        editSoilPH = findViewById(R.id.predict_soilPH_textInputEditText);
        autoDuration = findViewById(R.id.predict_duration_autoCompleteTextView);
        startFetch = findViewById(R.id.predict_fetch_weather_data_btn);
        startPrediction = findViewById(R.id.predict_start_prediction_btn);
        fetchAnimation = findViewById(R.id.predict_weather_fetch_complete_animation);
    }
}
