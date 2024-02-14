package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class avinash22 extends AppCompatActivity implements View.OnClickListener {
    public CardView street,water,san,road,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avinash22);
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
                i=new Intent(this,streetlightraise.class);
                startActivity(i);
                break;

            case R.id.wt:
                i=new Intent(this,watertapraise.class);
                startActivity(i);
                break;

            case R.id.san:
                i=new Intent(this,sanitizationraise.class);
                startActivity(i);
                break;

            case R.id.road:
                i=new Intent(this,roadraise.class);
                startActivity(i);
                break;
            case R.id.other:
                i=new Intent(this,otherraise.class);
                startActivity(i);
                break;
        }

    }


}