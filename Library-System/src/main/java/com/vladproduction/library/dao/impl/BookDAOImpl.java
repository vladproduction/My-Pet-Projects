package com.vladproduction.library.dao.impl;

import com.vladproduction.library.dao.BookDAO;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookDAOImpl implements BookDAO {

    private static final Logger logger = Logger.getLogger("LibraryLogger");
    private Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author_id, published_year) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor().getId()); // Assuming author is already fetched and set
            statement.setInt(3, book.getPublishedYear());
            statement.executeUpdate();

            // Optionally add the book to the author's list
            book.getAuthor().addBook(book); // Maintain relationship in memory
            logger.info("Added book: " + book.getTitle());
        } catch (SQLException e) {
            logger.severe("Error adding book: " + e.getMessage());
        }

    }

    @Override
    public Book getBook(int id) {
        String sql = "SELECT b.id, b.title, b.published_year, a.id AS author_id, a.name, a.nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id WHERE b.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        author,
                        resultSet.getInt("published_year")
                );
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving book: " + e.getMessage());
        }
        return null; // Return null if no book found
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.published_year, a.id AS author_id, a.name, a.nationality FROM books b JOIN authors a ON b.author_id = a.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        author,
                        resultSet.getInt("published_year")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving all books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author_id = ?, published_year = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor().getId()); // Assuming author exists
            statement.setInt(3, book.getPublishedYear());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
            logger.info("Updated book: " + book.getTitle());
        } catch (SQLException e) {
            logger.severe("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Deleted book with ID: " + id);
        } catch (SQLException e) {
            logger.severe("Error deleting book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getBooksByAuthorName(String authorName) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.published_year, a.id AS author_id, a.name, a.nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id WHERE a.name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + authorName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        author,
                        resultSet.getInt("published_year")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving books by author name: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getBooksByNationality(String nationality) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.published_year, a.id AS author_id, a.name, a.nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id WHERE a.nationality LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nationality + "%"); // Allows for partial matches
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        author,
                        resultSet.getInt("published_year")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving books by nationality: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getBooksByPublishedYear(int year) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.published_year, a.id AS author_id, a.name, a.nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id WHERE b.published_year = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        author,
                        resultSet.getInt("published_year")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving books by published year: " + e.getMessage());
        }
        return books;
    }

}
