package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity10 extends AppCompatActivity {
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        b1=(Button)findViewById(R.id.cl);
        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity();
            }
        }));
    }
    public void MainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}