package com.sohaibkhan.digitallibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AllBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recView = findViewById(R.id.recView);
        AllBookRecViewAdapter adapter = new AllBookRecViewAdapter(this, "allBook");

        adapter.setBookList(Utils.getInstance(this).getAllBooks());

        recView.setAdapter(adapter);

        recView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}