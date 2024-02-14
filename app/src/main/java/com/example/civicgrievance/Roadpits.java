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

public class Roadpits extends AppCompatActivity {
    RecyclerView recyclerView;
    //myadapter madapter;
    roadadapter roadadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadpits);


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Filter").child("RoadProblems"), model.class)
                        .build();
        roadadapter =new roadadapter(options);
        recyclerView.setAdapter(roadadapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        roadadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        roadadapter.stopListening();
    }
}