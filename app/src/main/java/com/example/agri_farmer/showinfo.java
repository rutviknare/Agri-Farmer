package com.example.agri_farmer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class showinfo extends AppCompatActivity {

    TextView closeicon,heading,paragraph;
    ImageView cropimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);
        closeicon=findViewById(R.id.txtclose);
        heading=findViewById(R.id.cropheading);
        paragraph=findViewById(R.id.cropparagraph);
        cropimage=findViewById(R.id.cropimage);




}
}
