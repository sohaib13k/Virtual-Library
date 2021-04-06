package com.sohaibkhan.digitallibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String ALL_BOOK_KEY = "all_books";
    private static final String CURRENT_READING_KEY = "current_reading";
    private static final String ALREADY_READ_KEY = "already_read";
    private static final String WISHLIST_KEY = "wishlist";
    private static final String FAVOURITE_KEY = "favourite";

    private static Utils instance;

    private SharedPreferences sharedPreferences;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("database_SP", Context.MODE_PRIVATE);

        if (getAllBooks() == null)
            initData();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getCurrentlyReading() == null) {

            editor.putString(CURRENT_READING_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getAlreadyRead() == null) {

            editor.putString(ALREADY_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWishlist() == null) {

            editor.putString(WISHLIST_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavourite() == null) {
            editor.putString(FAVOURITE_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1,
                "Quran",
                "Allah",
                600,
                "https://kbimages1-a.akamaihd.net/85f1c564-66d7-4557-993a-4ebfe01cf3f4/353/569/90/False/the-holy-quran-traslated-by-yusuf-ali-2.jpg",
                "The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events.",
                "Book for whole humanity The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events",
                "http://go.lifehack.org/?id=26904X855817&isjs=1&jv=14.4.0-stackpath&sref=https%3A%2F%2Fwww.lifehack.org%2Farticles%2Fcommunication%2F30-books-that-everyone-should-read-least-once-their-lives.html&url=https%3A%2F%2Fwww.amazon.com%2Fdp%2FB00K1XOV5G%3Ftag%3Ds7621-20&xguid=01EEQM8G8RCTNB7E8RGYHTAZ7Z&xs=1&xtz=-330&xuuid=f57147555e41c7995d781a1a74cd2685&abp=1&xjsf=other_click__auxclick%20%5B2%5D"));


        books.add(new Book(2,
                "Hadees",
                "Mohammad",
                200,
                "https://kbimages1-a.akamaihd.net/85f1c564-66d7-4557-993a-4ebfe01cf3f4/353/569/90/False/the-holy-quran-traslated-by-yusuf-ali-2.jpg",
                "The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events.",
                "Book for whole humanity",
                "http://go.lifehack.org/?id=26904X855817&isjs=1&jv=14.4.0-stackpath&sref=https%3A%2F%2Fwww.lifehack.org%2Farticles%2Fcommunication%2F30-books-that-everyone-should-read-least-once-their-lives.html&url=https%3A%2F%2Fwww.amazon.com%2Fdp%2FB008DM45VC%3Ftag%3Ds7621-20&xguid=01EEQM8G8RCTNB7E8RGYHTAZ7Z&xs=1&xtz=-330&xuuid=f57147555e41c7995d781a1a74cd2685&abp=1&xjsf=other_click__auxclick%20%5B2%5D"));

        books.add(new Book(3,
                "Tafseer",
                "Maududi",
                700,
                "https://kbimages1-a.akamaihd.net/85f1c564-66d7-4557-993a-4ebfe01cf3f4/353/569/90/False/the-holy-quran-traslated-by-yusuf-ali-2.jpg",
                "The Quran assumes familiarity with major narratives recounted in the Biblical and apocryphal scriptures. It summarizes some, dwells at length on others and, in some cases, presents alternative accounts and interpretations of events.",
                "Book for whole humanity",
                "http://go.lifehack.org/?id=26904X855817&isjs=1&jv=14.4.0-stackpath&sref=https%3A%2F%2Fwww.lifehack.org%2Farticles%2Fcommunication%2F30-books-that-everyone-should-read-least-once-their-lives.html&url=https%3A%2F%2Fwww.amazon.com%2Fdp%2FB019PIOJYU%3Ftag%3Ds7621-20&xguid=01EEQM8G8RCTNB7E8RGYHTAZ7Z&xs=1&xtz=-330&xuuid=f57147555e41c7995d781a1a74cd2685&abp=1&xjsf=other_click__auxclick%20%5B2%5D"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOK_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (instance == null)
            instance = new Utils(context);
        return instance;
    }

    public Book getBookDetailByID(int id) {
        ArrayList<Book> books = getAllBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == id)
                    return b;
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReading() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENT_READING_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyRead() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWishlist() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISHLIST_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavourite() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_KEY, null), type);
        return books;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();
        if (books != null) {
            if (books.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENT_READING_KEY);
                Gson gson = new Gson();
                editor.putString(CURRENT_READING_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyRead();
        if (books != null) {
            if (books.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_KEY);
                Gson gson = new Gson();
                editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishlist(Book book) {
        ArrayList<Book> books = getWishlist();
        if (books != null) {
            if (books.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_KEY);
                Gson gson = new Gson();
                editor.putString(WISHLIST_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavourite(Book book) {
        ArrayList<Book> books = getFavourite();
        if (books != null) {
            if (books.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_KEY);
                Gson gson = new Gson();
                editor.putString(FAVOURITE_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();
        for (Book b : books) {
            if (book.getId() == b.getId()) {
                if (books.remove(b)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CURRENT_READING_KEY);
                    Gson gson = new Gson();
                    editor.putString(CURRENT_READING_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyRead();
        for (Book b : books) {
            if (book.getId() == b.getId()) {
                if (books.remove(b)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADY_READ_KEY);
                    Gson gson = new Gson();
                    editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeFromWishlist(Book book) {
        ArrayList<Book> books = getWishlist();
        for (Book b : books) {
            if (book.getId() == b.getId()) {
                if (books.remove(b)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(WISHLIST_KEY);
                    Gson gson = new Gson();
                    editor.putString(WISHLIST_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeFromFavourite(Book book) {
        ArrayList<Book> books = getFavourite();
        for (Book b : books) {
            if (book.getId() == b.getId()) {
                if (books.remove(b)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(FAVOURITE_KEY);
                    Gson gson = new Gson();
                    editor.putString(FAVOURITE_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Book book, String contextNameTemp) {
        if (contextNameTemp == "currentlyReading")
            return removeFromCurrentlyReading(book);
        else if (contextNameTemp == "alreadyRead")
            return removeFromAlreadyRead(book);
        else if (contextNameTemp == "Wishlist")
            return removeFromWishlist(book);
        else if (contextNameTemp == "Favourite")
            return removeFromFavourite(book);
        else
            return false;
    }
}