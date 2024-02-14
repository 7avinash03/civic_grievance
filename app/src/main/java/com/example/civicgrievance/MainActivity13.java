package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        TextView tv=(TextView) findViewById(R.id.scrollview1);
        tv.setSelected(true);
        tv.setScrollBarFadeDuration(10);
    }
}