package com.example.konyvtarasztali;

public class Konyv {
    private int id;
    private String title;
    private String author;
    private int oublish_year;
    private int page_count;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getOublish_year() {
        return oublish_year;
    }

    public int getPage_count() {
        return page_count;
    }

    public Konyv(int id, String title, String author, int oublish_year, int page_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.oublish_year = oublish_year;
        this.page_count = page_count;
    }

    @Override
    public String toString() {
        return String.format("\tSzerző: %s"+
                "\n\tCím: %s"+
                "\n\tKiadás éve: %d" +
                "\n\tOldalszám: %d",
                this.author,this.title, this.oublish_year,this.getPage_count());
    }
}
