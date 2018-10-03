package com.example.zach.cs196fall2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.Adapter adapt;
    private RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent myIntent = getIntent();
        String text = myIntent.getStringExtra("EXTRA_DATA");
        recView = findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        adapt = new MyAdapter(Person.people.toArray(new Person[Person.people.size()]));
        recView.setAdapter(adapt);

    }
}
