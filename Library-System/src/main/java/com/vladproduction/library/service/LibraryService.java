package com.vladproduction.library.service;

import com.vladproduction.library.dao.AuthorDAO;
import com.vladproduction.library.dao.BookDAO;
import com.vladproduction.library.dao.impl.AuthorDAOImpl;
import com.vladproduction.library.dao.impl.BookDAOImpl;
import com.vladproduction.library.logger.LoggerUtil;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

public class LibraryService {
    private static final Logger logger = LoggerUtil.getLogger(); // Get the logger from LoggerUtil
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;

    // Constructor to inject DAO dependencies
    public LibraryService(Connection connection) {
        this.bookDAO = new BookDAOImpl(connection);
        this.authorDAO = new AuthorDAOImpl(connection);
    }

    // Book related methods
    public void addBook(Book book) {
        bookDAO.addBook(book);
        logger.info("Book added: " + book.getTitle());
    }

    public Book getBook(int id) {
        Book book = bookDAO.getBook(id);
        if (book != null) {
            logger.info("Retrieved book: " + book.getTitle());
        } else {
            logger.warning("Book with ID " + id + " not found.");
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        logger.info("Retrieved all books. Count: " + books.size());
        return books;
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
        logger.info("Book updated: " + book.getTitle());
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
        logger.info("Book deleted with ID: " + id);
    }

    // Author related methods
    public void addAuthor(Author author) {
        authorDAO.addAuthor(author);
        logger.info("Author added: " + author.getName());
    }

    public Author getAuthor(int id) {
        Author author = authorDAO.getAuthor(id);
        if (author != null) {
            logger.info("Retrieved author: " + author.getName());
        } else {
            logger.warning("Author with ID " + id + " not found.");
        }
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        logger.info("Retrieved all authors. Count: " + authors.size());
        return authors;
    }

    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
        logger.info("Author updated: " + author.getName());
    }

    public void deleteAuthor(int id) {
        authorDAO.deleteAuthor(id);
        logger.info("Author deleted with ID: " + id);
    }
}
