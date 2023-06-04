package com.example.agri_farmer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
//import com.example.agrifarmer.C0130R;
//import com.example.agrifarmer.params.CropRecordParams;
import java.util.HashMap;

public class ShowResultActivity extends AppCompatActivity {
    private TextView crop1;
    private TextView crop2;
    private TextView crop3;
    private TextView duration;
    private ImageView icrop1;
    private ImageView icrop2;
    private ImageView icrop3;
    private TextView location;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_show_result);
        getWindow().setFlags(1024, 1024);
        setUIViews();
        Intent intent = getIntent();
        this.location.setText(intent.getStringExtra("location"));
        this.duration.setText("Duration: " + intent.getStringExtra("duration") + " months");
        String strCrop1 = intent.getStringExtra(CropRecordParams.KEY_CROP1);
        String strCrop2 = intent.getStringExtra(CropRecordParams.KEY_CROP2);
        String strCrop3 = intent.getStringExtra(CropRecordParams.KEY_CROP3);
        this.crop1.setText(strCrop1);
        this.crop2.setText(strCrop2);
        this.crop3.setText(strCrop3);
        HashMap<String, Drawable> crops = new HashMap<>();
        crops.put("rice", AppCompatResources.getDrawable(this, R.drawable.rice));
        crops.put("bajra", AppCompatResources.getDrawable(this, R.drawable.bajra));
        crops.put("cotton", AppCompatResources.getDrawable(this, R.drawable.cotton));
        crops.put("gram", AppCompatResources.getDrawable(this, R.drawable.gram));
        crops.put("groundnut", AppCompatResources.getDrawable(this, R.drawable.groundnut));
        crops.put("jowar", AppCompatResources.getDrawable(this, R.drawable.jowar));
        crops.put("maize", AppCompatResources.getDrawable(this, R.drawable.maize));
        crops.put("ragi", AppCompatResources.getDrawable(this, R.drawable.ragi));
        crops.put("soyabean", AppCompatResources.getDrawable(this, R.drawable.soyabean));
        crops.put("sugarcane", AppCompatResources.getDrawable(this, R.drawable.sugarcane));
        crops.put("wheat", AppCompatResources.getDrawable(this, R.drawable.wheat));
        this.icrop1.setImageDrawable(crops.get(strCrop1.toLowerCase()));
        this.icrop2.setImageDrawable(crops.get(strCrop2.toLowerCase()));
        this.icrop3.setImageDrawable(crops.get(strCrop3.toLowerCase()));
    }

    private void setUIViews() {
        this.location = (TextView) findViewById(R.id.result_location);
        this.duration = (TextView) findViewById(R.id.result_duration);
        this.crop1 = (TextView) findViewById(R.id.result_crop1);
        this.crop2 = (TextView) findViewById(R.id.result_crop2);
        this.crop3 = (TextView) findViewById(R.id.result_crop3);
        this.icrop1 = (ImageView) findViewById(R.id.result_crop1_iv);
        this.icrop2 = (ImageView) findViewById(R.id.result_crop2_iv);
        this.icrop3 = (ImageView) findViewById(R.id.result_crop3_iv);
    }
}
