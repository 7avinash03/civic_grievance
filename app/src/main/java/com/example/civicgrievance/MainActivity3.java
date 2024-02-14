package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.Login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity3.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                    auth();
                } else
                    Toast.makeText(MainActivity3.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void others() {
        Intent intent = new Intent(this, others.class);
        startActivity(intent);
    }
    public void avinash() {
        Intent intent = new Intent(this, avinash.class);
        startActivity(intent);
    }
    public void auth() {
        Intent intent = new Intent(this, auth.class);
        startActivity(intent);
    }
}