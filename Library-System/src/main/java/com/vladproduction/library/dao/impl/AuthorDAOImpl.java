package com.vladproduction.library.dao.impl;

import com.vladproduction.library.dao.AuthorDAO;
import com.vladproduction.library.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AuthorDAOImpl implements AuthorDAO {

    private static final Logger logger = Logger.getLogger("LibraryLogger");
    private Connection connection;

    // Constructor
    public AuthorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAuthor(Author author) {
        String sql = "INSERT INTO authors (name, nationality) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getNationality());
            statement.executeUpdate();

            // Get the generated keys (ID)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1)); // set generated ID
            }
            logger.info("Added author: " + author.getName());
        } catch (SQLException e) {
            logger.severe("Error adding author: " + e.getMessage());
        }
    }

    @Override
    public Author getAuthor(int id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving author: " + e.getMessage());
        }
        return null; // Return null if no author found
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nationality")
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving all authors: " + e.getMessage());
        }
        return authors;
    }

    @Override
    public void updateAuthor(Author author) {
        String sql = "UPDATE authors SET name = ?, nationality = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getNationality());
            statement.setInt(3, author.getId());
            statement.executeUpdate();
            logger.info("Updated author: " + author.getName());
        } catch (SQLException e) {
            logger.severe("Error updating author: " + e.getMessage());
        }
    }

    @Override
    public void deleteAuthor(int id) {
        // First, delete all books associated with this author
        String deleteBooksSQL = "DELETE FROM books WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteBooksSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error deleting books for author ID " + id + ": " + e.getMessage());
        }

        // Now delete the author
        String sql = "DELETE FROM authors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Deleted author with ID: " + id);
        } catch (SQLException e) {
            logger.severe("Error deleting author: " + e.getMessage());
        }
    }
}
