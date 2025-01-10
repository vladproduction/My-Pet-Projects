package com.vladproduction.library.model;

public class Book {
    private int id;
    private String title;
    private Author author; // Reference to Author
    private int publishedYear;

    public Book(int id, String title, Author author, int publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString(){
        return String.format("Book [id: %d, title: %s, author: %s, publishedYear: %d]",
                this.id, this.title, this.author.getName(), this.publishedYear);
    }
}
