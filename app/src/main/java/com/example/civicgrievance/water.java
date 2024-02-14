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

public class water extends AppCompatActivity {
    RecyclerView recyclerView;
    //myadapter madapter;
    wateradapter wateradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Filter").child("WatertapProblems"), model.class)
                        .build();
        wateradapter =new wateradapter(options);
        recyclerView.setAdapter(wateradapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        wateradapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wateradapter.stopListening();
    }
}