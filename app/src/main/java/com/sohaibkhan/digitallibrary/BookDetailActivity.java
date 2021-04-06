package com.sohaibkhan.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class BookDetailActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookID";

    private TextView bookTitle, author, pages, shortDesc, longDesc, url;
    private ImageView imgViewCover;
    private Button btnCurrentlyReading, btnAlreadyRead, btnWishlist, btnFavourite;
    private RelativeLayout relLayoutParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (id != -1) {
                Book book = Utils.getInstance(this).getBookDetailByID(id);
                if (book != null) {
                    setBookData(book);

                    currentlyReading(book);
                    alreadyRead(book);
                    wishlist(book);
                    Favourite(book);

                    urlRedirect(book);
                }
            }
        }
    }

    private void urlRedirect(final Book book) {
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, AboutActivity.class);
                intent.putExtra("url", book.getUrl());
                startActivity(intent);
            }
        });
    }

    private void currentlyReading(final Book book) {
        boolean found = false;

        for (Book b : Utils.getInstance(this).getCurrentlyReading()) {
            if (book.getId() == b.getId()) {
                found = true;
                break;
            }
        }

        if (found) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookDetailActivity.this).addToCurrentlyReading(book)) {
                        btnCurrentlyReading.setEnabled(false);
                        Snackbar.make(relLayoutParent, "Book added in Current Reading list", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Open Reading list", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BookDetailActivity.this, CurrentReadingActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }
            });
        }
    }

    private void alreadyRead(final Book book) {
        boolean found = false;

        for (Book b : Utils.getInstance(this).getAlreadyRead()) {
            if (book.getId() == b.getId()) {
                found = true;
                break;
            }
        }

        if (found) {
            btnAlreadyRead.setEnabled(false);
        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookDetailActivity.this).addToAlreadyRead(book)) {
                        btnAlreadyRead.setEnabled(false);
                        Snackbar.make(relLayoutParent, "Book added in Already Read list", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Open Already Read list", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BookDetailActivity.this, AlreadyReadActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }
            });
        }



    }

    private void wishlist(final Book book) {
        boolean found = false;

        for (Book b : Utils.getInstance(this).getWishlist()) {
            if (book.getId() == b.getId()) {
                found = true;
                break;
            }
        }

        if (found) {
            btnWishlist.setEnabled(false);
        } else {
            btnWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookDetailActivity.this).addToWishlist(book)) {
                        btnWishlist.setEnabled(false);
                        Snackbar.make(relLayoutParent, "Book added to Wishlist", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Open Wishlist", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BookDetailActivity.this, WishlistActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }
            });
        }
    }

    private void Favourite(final Book book) {
        boolean found = false;

        for (Book b : Utils.getInstance(this).getFavourite()) {
            if (book.getId() == b.getId()) {
                found = true;
                break;
            }
        }

        if (found) {
            btnFavourite.setEnabled(false);
        } else {
            btnFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookDetailActivity.this).addToFavourite(book)) {
                        btnFavourite.setEnabled(false);
                        Snackbar.make(relLayoutParent, "Book added to Favourites", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Open Favourites", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BookDetailActivity.this, FavouriteActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }
            });
        }
    }

    private void initViews() {
        bookTitle = findViewById(R.id.txtView_title);
        author = findViewById(R.id.txtView_author);
        pages = findViewById(R.id.txtView_pages);
        shortDesc = findViewById(R.id.txtView_shortDesc);
        longDesc = findViewById(R.id.txtView_longDesc);
        url = findViewById(R.id.textView_url);

        imgViewCover = findViewById(R.id.imageViewCover);

        btnCurrentlyReading = findViewById(R.id.btn_currently_reading);
        btnAlreadyRead = findViewById(R.id.btn_already_read);
        btnWishlist = findViewById(R.id.btn_wishlist);
        btnFavourite = findViewById(R.id.btn_add_to_favourite);

        relLayoutParent = findViewById(R.id.parent_book_details_activity);
    }

    private void setBookData(Book book) {
        bookTitle.setText(book.getTitle());
        author.setText(book.getAuthor());
        pages.setText(String.valueOf(book.getPages()));
        url.setText(book.getUrl());
        shortDesc.setText(book.getShortDesc());
        longDesc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(imgViewCover);

    }
}