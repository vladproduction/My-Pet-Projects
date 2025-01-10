package com.vladproduction.library.dao;

import com.vladproduction.library.model.Book;

import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book getBook(int id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);
    List<Book> getBooksByAuthorName(String authorName); // New method
    List<Book> getBooksByNationality(String nationality); // New method
    List<Book> getBooksByPublishedYear(int year); // New method

}
