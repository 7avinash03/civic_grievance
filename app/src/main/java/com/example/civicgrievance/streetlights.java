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

public class streetlights extends AppCompatActivity {
    RecyclerView recyclerView;
    streetadapter streetadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Filter").child("StreetlightProblems"), model.class)
                        .build();
        streetadapter =new streetadapter(options);
        recyclerView.setAdapter(streetadapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        streetadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        streetadapter.stopListening();
    }
}