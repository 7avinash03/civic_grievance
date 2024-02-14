package com.example.civicgrievance;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public CardView card1,card2,card3,card4;
    private static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card1=(CardView) findViewById(R.id.people);
        card2=(CardView) findViewById(R.id.authority);
        card3=(CardView) findViewById(R.id.contact);
        card4=(CardView) findViewById(R.id.help);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent i;
        switch(v.getId()){
            case R.id.people:
                i=new Intent(this,MainActivity9.class);
                startActivity(i);
                break;

            case R.id.authority:
                i=new Intent(this,MainActivity3.class);
                startActivity(i);
                break;

            case R.id.contact:
                CallButton();
                break;

            case R.id.help:
                i=new Intent(this,MainActivity13.class);
                startActivity(i);
                break;
        }

    }

    private void CallButton() {
        String number = "8074529629";
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }


    public void onRequestPermissionResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            CallButton();
        }else
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
    }
}
