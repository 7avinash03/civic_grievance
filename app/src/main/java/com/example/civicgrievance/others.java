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

public class others extends AppCompatActivity {
    RecyclerView recyclerView;
    otheradapter otheradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Filter").child("otherProblems"), model.class)
                        .build();

        otheradapter =new otheradapter(options);
        recyclerView.setAdapter(otheradapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        otheradapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        otheradapter.stopListening();
    }
}