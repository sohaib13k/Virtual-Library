package com.sohaibkhan.digitallibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBook, btnCurrentlyReading, btnRead, btnWishlist, btnFavourite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBookActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentReadingActivity.class);
                startActivity(intent);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadActivity.class);
                startActivity(intent);
            }
        });

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Virtual Library");
                builder.setMessage("This is an application for virtually storing and tracking of books." +
                        "This has been developed by Sohaib Khan.");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        intent.putExtra("url","https://gmail.com");
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    private void initViews() {
        btnAllBook = findViewById(R.id.btn_all_book);
        btnCurrentlyReading = findViewById(R.id.btn_current_reading);
        btnRead = findViewById(R.id.btn_already_read);
        btnWishlist = findViewById(R.id.btn_wishlist);
        btnFavourite = findViewById(R.id.btn_favourite);
        btnAbout = findViewById(R.id.btn_about);
    }
}