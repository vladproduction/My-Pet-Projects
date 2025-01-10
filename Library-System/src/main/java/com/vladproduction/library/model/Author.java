package com.vladproduction.library.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private String nationality;
    private List<Book> books; // List to store books written by the author

    // Constructor
    public Author(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.books = new ArrayList<>(); // Initialize the list of books
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return String.format("Author [id: %d, name: %s, nationality: %s]",
                this.id, this.name, this.nationality);
    }

}
