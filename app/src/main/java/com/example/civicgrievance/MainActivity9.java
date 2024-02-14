package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity9 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
    }

    public void setLanguage(Activity activity, String language) {
        Locale locale = new Locale(language);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.english: {
                setLanguage(this, "en");
                startActivity(new Intent(this, avinash22.class));
                break;
            }
            case R.id.telugu: {
                setLanguage(this, "te");
                startActivity(new Intent(this, avinash22.class));
                break;
            }
        }
    }
}