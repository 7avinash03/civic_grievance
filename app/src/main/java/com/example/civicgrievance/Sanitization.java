package com.example.civicgrievance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Sanitization extends AppCompatActivity {
    RecyclerView recyclerView;
    santiadapter santiadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitization);
        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Filter").child("SanitizationProblems"), model.class)
                        .build();
        santiadapter =new santiadapter(options);
        recyclerView.setAdapter(santiadapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        santiadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        santiadapter.stopListening();
    }
}