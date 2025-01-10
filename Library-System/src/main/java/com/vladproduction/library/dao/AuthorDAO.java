package com.vladproduction.library.dao;

import com.vladproduction.library.model.Author;

import java.util.List;

public interface AuthorDAO {
    void addAuthor(Author author);
    Author getAuthor(int id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(int id);
}
