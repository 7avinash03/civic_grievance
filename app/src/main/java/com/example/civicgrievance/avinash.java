package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class avinash extends AppCompatActivity implements View.OnClickListener {
    public CardView street,water,san,road,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avinash);
        street=(CardView) findViewById(R.id.sl);
        water=(CardView) findViewById(R.id.wt);
        san=(CardView) findViewById(R.id.san);
        road=(CardView) findViewById(R.id.road);
        other=(CardView) findViewById(R.id.other);
        street.setOnClickListener(this);
        water.setOnClickListener(this);
        san.setOnClickListener(this);
        road.setOnClickListener(this);
        other.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        Intent i;
        switch(v.getId()){
            case R.id.sl:
                i=new Intent(this,streetlights.class);
                startActivity(i);
                break;

            case R.id.wt:
                i=new Intent(this,water.class);
                startActivity(i);
                break;

            case R.id.san:
                i=new Intent(this,Sanitization.class);
                startActivity(i);
                break;

            case R.id.road:
                i=new Intent(this,Roadpits.class);
                startActivity(i);
                break;
            case R.id.other:
                i=new Intent(this,others.class);
                startActivity(i);
                break;
        }

    }


}