package com.sohaibkhan.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.widget.LinearLayout;

public class CurrentReadingActivity extends AppCompatActivity {

    private RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reading);

        recView = findViewById(R.id.recViewCurrentReading);
        AllBookRecViewAdapter adapter = new AllBookRecViewAdapter(this, "currentlyReading");

        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBookList(Utils.getInstance(this).getCurrentlyReading());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}