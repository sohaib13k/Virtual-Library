package com.sohaibkhan.digitallibrary;

public class Book {
    private int id;
    private String title;
    private String author;
    private int pages;
    private String imageURL;
    private String shortDesc;
    private String longDesc;
    private String url;

    public Book(int id, String title, String author, int pages, String imageURL, String shortDesc, String longDesc, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imageURL = imageURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imageURL='" + imageURL + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
