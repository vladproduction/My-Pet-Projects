package com.vladproduction.library.app;

import com.vladproduction.library.dao.AuthorDAO;
import com.vladproduction.library.dao.BookDAO;
import com.vladproduction.library.dao.impl.AuthorDAOImpl;
import com.vladproduction.library.dao.impl.BookDAOImpl;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.service.LibraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * cases for various user interactions based on the menu options;
 * */
class LibraryAppUserInteractionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibraryService libraryService;
    private AuthorDAO authorDAO;
    private BookDAO bookDAO;
    private Connection connection;

    @BeforeEach
    void setUp() {
        // Initialize H2 in-memory database connection
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

            // Create DAO instances
            authorDAO = new AuthorDAOImpl(connection);
            bookDAO = new BookDAOImpl(connection);
            libraryService = new LibraryService(bookDAO, authorDAO);

            // Set up Database schema for testing
            try (Statement stmt = connection.createStatement()) {
                // Drop existing tables to avoid conflicts
                stmt.execute("DROP TABLE IF EXISTS books;");
                stmt.execute("DROP TABLE IF EXISTS authors;");
                // Create tables
                stmt.execute("CREATE TABLE authors (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, nationality VARCHAR(100));");
                stmt.execute("CREATE TABLE books (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255) NOT NULL, author_id INT, published_year INT, FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE);");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Clean up the connection and restore System.out
        try {
            if (connection != null) {
                connection.close();
            }
            System.setOut(originalOut); // Restore original System.out
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddAuthor() {
        // Simulate user input for adding the author
        String simulatedInput = "yes\nJohn Doe\nAmerican\n"; // Simulated input for adding an author
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes())); // Redirect input

        // Set up the in-memory database environment for each test to avoid interference with existing data
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")) {
            AuthorDAO authorDAO = new AuthorDAOImpl(connection);
            BookDAO bookDAO = new BookDAOImpl(connection);
            LibraryService libraryService = new LibraryService(bookDAO, authorDAO);

            // Create tables
            try (Statement stmt = connection.createStatement()) {
                // Drop existing tables to avoid conflicts

                stmt.execute("CREATE TABLE authors (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, nationality VARCHAR(100));");
                stmt.execute("CREATE TABLE books (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255) NOT NULL, author_id INT, published_year INT, FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE);");
            }

            // Run the LibraryApp main method to start the application
            String[] args = {}; // No command-line arguments are needed
            LibraryApp.main(args); // Call main to simulate running the app

            // Check output for confirmation of adding author
            String output = outContent.toString();
            assertTrue(output.contains("Author added successfully: John Doe"), "Output should confirm that the author was added.");

            // Optionally, validate that the author is retrieved correctly after being added
            Author retrievedAuthor = libraryService.getAuthor(1); // Assuming ID is set to 1
            assertNotNull(retrievedAuthor, "The author should be retrievable after being added.");
            assertEquals("John Doe", retrievedAuthor.getName());
            assertEquals("American", retrievedAuthor.getNationality());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}