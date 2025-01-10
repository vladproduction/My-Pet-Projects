package com.vladproduction.library.service;

import com.vladproduction.library.dao.AuthorDAO;
import com.vladproduction.library.dao.BookDAO;
import com.vladproduction.library.dao.impl.AuthorDAOImpl;
import com.vladproduction.library.dao.impl.BookDAOImpl;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1) Utilize an H2 in-memory database to provide a lightweight testing environment;
 * 2) Tests for the following scenarios:
 * + Adding authors and ensuring they can be retrieved.
 * + Adding books and verifying they associate correctly with authors.
 * + Updating authors and books.
 * + Deleting authors and ensuring related books are also deleted.
 * + Edge cases, such as trying to delete a non-existent author or book.
 * 3) Use JUnit's test lifecycle (i.e., @BeforeEach and @AfterEach) to set up and clean the database state for each test;
 * */
public class LibraryServiceIntegrationTest {

    private LibraryService libraryService;
    private Connection connection;

    @BeforeEach
    void setUp() {
        try {
            // Initialize H2 database connection
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

            // Initialize DAO instances with the connection
            AuthorDAO authorDAO = new AuthorDAOImpl(connection);
            BookDAO bookDAO = new BookDAOImpl(connection);

            // Create LibraryService with actual DAO implementations
            libraryService = new LibraryService(bookDAO, authorDAO);

            // Set up Database schema for testing
            try (Statement stmt = connection.createStatement()) {
                // Drop tables if they already exist to avoid conflicts
                stmt.execute("DROP TABLE IF EXISTS books;");
                stmt.execute("DROP TABLE IF EXISTS authors;");

                // Create authors table
                stmt.execute("CREATE TABLE authors (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, nationality VARCHAR(100));");
                // Create books table
                stmt.execute("CREATE TABLE books (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255) NOT NULL, author_id INT, published_year INT, FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE);");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddAuthorAndRetrieve() {
        Author author = new Author(0, "Jane Doe", "Canadian"); // Use 0 for the ID to let the system generate it
        libraryService.addAuthor(author); // Add author

        // Now retrieve the author by the generated ID (after adding, it should have an ID)
        Author retrievedAuthor = libraryService.getAuthor(author.getId());

        // Ensure the retrieved author matches
        assertNotNull(retrievedAuthor, "Expected to find the author");
        assertEquals(author.getName(), retrievedAuthor.getName());
        assertEquals(author.getNationality(), retrievedAuthor.getNationality());
    }

    @Test
    void testAddBookAndRetrieve() {
        Author author = new Author(1, "John Smith", "American");
        libraryService.addAuthor(author); // Ensure the author is added first

        Book book = new Book(0, "Java Basics", author, 2022); // ID will be auto-generated
        libraryService.addBook(book); // Call the service method to add the book

        // Now retrieve the book by its generated ID
        Book retrievedBook = libraryService.getBook(book.getId());
        assertNotNull(retrievedBook, "Expected to find the book");
        assertEquals(book.getTitle(), retrievedBook.getTitle());
        assertEquals(author.getId(), retrievedBook.getAuthor().getId());
    }

    @Test
    void testDeleteAuthorAndRelatedBooks() {
        Author author = new Author(0, "Mark Twain", "American");
        libraryService.addAuthor(author);

        Book book1 = new Book(0, "The Adventures of Tom Sawyer", author, 1876);
        Book book2 = new Book(0, "Adventures of Huckleberry Finn", author, 1884);
        libraryService.addBook(book1);
        libraryService.addBook(book2);

        // Ensure books were added
        List<Book> books = libraryService.getBooksByAuthorName(author.getName());
        assertEquals(2, books.size());

        // Now delete the author
        libraryService.deleteAuthor(author.getId());

        // Check if the books are deleted as well
        List<Book> remainingBooks = libraryService.getBooksByAuthorName(author.getName());
        assertTrue(remainingBooks.isEmpty(), "Books should be deleted when author is deleted.");
    }

    @Test
    void testUpdateAuthor() {
        // Arrange
        Author originalAuthor = new Author(1, "John Doe", "American");
        libraryService.addAuthor(originalAuthor); // Add the original author first

        // Update author's details
        originalAuthor.setNationality("British");
        libraryService.updateAuthor(originalAuthor); // Call to update

        // Retrieve the updated author
        Author updatedAuthor = libraryService.getAuthor(originalAuthor.getId());

        // Assert that the updated author has the new nationality
        assertEquals("British", updatedAuthor.getNationality(), "Author's nationality should have been updated.");
    }

    @Test
    void testUpdateBook() {
        // Arrange
        Author author = new Author(1, "Jane Doe", "Canadian");
        libraryService.addAuthor(author); // Add author first

        Book book = new Book(1, "Learning Java", author, 2022); // Create a book for that author
        libraryService.addBook(book); // Now add the book

        // Update book details
        book.setTitle("Learning Advanced Java");
        book.setPublishedYear(2023); // Update the published year

        libraryService.updateBook(book); // Call to update the book

        // Retrieve the updated book
        Book updatedBook = libraryService.getBook(book.getId());

        // Assert that the updated book has the new title and year
        assertEquals("Learning Advanced Java", updatedBook.getTitle(), "Book's title should have been updated.");
        assertEquals(2023, updatedBook.getPublishedYear(), "Book's published year should have been updated.");
    }

    @Test
    void deleteNonExistentAuthor() {
        int nonExistentAuthorId = 999; // An ID that does not exist in the database

        // Attempt to delete a non-existent author
        libraryService.deleteAuthor(nonExistentAuthorId); // Call delete method

        // Verify that the delete method was called on the DAO with the appropriate ID
        // We don’t expect it to throw an exception or fail
        // Optionally, you might want to capture log messages if any
        // Or check some state if you have a specific way to handle non-existent entities
    }

    @Test
    void deleteNonExistentBook() {
        int nonExistentBookId = 999; // An ID that does not exist in the database

        // Attempt to delete a non-existent book
        libraryService.deleteBook(nonExistentBookId); // Call delete method

        // Same as with the author: we don’t expect it to fail or throw an exception
        // Optionally, capture log messages to verify correct logging behavior
    }

}
