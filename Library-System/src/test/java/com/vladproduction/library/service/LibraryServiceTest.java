package com.vladproduction.library.service;

import com.vladproduction.library.dao.AuthorDAO;
import com.vladproduction.library.dao.BookDAO;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    private LibraryService libraryService;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private Connection connection;

    @BeforeEach
    void setUp() {
        try {
            // Initialize H2 database connection
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

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

            // Create mock DAO instances
            authorDAO = Mockito.mock(AuthorDAO.class);
            bookDAO = Mockito.mock(BookDAO.class); // Initialize mocked BookDAO

            // Create LibraryService with mocked DAOs
            libraryService = new LibraryService(bookDAO, authorDAO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addBook() {
        Author author = new Author(1, "John Doe", "American"); // Mock author
        Book book = new Book(0, "Learning Java", author, 2022);

        // Execute the method to add a book
        libraryService.addBook(book);

        // Verify that the addBook method on the bookDAO was called
        verify(bookDAO).addBook(book); // If using mocks, ensure to call the mocked method.
    }

    @Test
    void getBook() {
        // Setup mock behavior
        Author author = new Author(1, "John Doe", "American");
        Book book = new Book(1, "Learning Java", author, 2022);
        when(bookDAO.getBook(1)).thenReturn(book); // Mock DAO to return the book

        // Call the method under test
        Book retrievedBook = libraryService.getBook(1);
        assertEquals(book, retrievedBook); // Verify that the retrieved book matches what we expect
    }

    @Test
    void getAllBooks() {
        List<Book> books = new ArrayList<>();
        Author author = new Author(1, "John Doe", "American");
        books.add(new Book(1, "Learning Java", author, 2022));
        books.add(new Book(2, "Advanced Java", author, 2023));

        // Setup mock behavior
        when(bookDAO.getAllBooks()).thenReturn(books); // Mock DAO to return list of books

        List<Book> retrievedBooks = libraryService.getAllBooks();
        assertEquals(2, retrievedBooks.size()); // Verify the size of the books list
        assertEquals(books, retrievedBooks); // Verify that the actual books retrieved match what we expect
    }

    @Test
    void updateBook() {
        Author author = new Author(1, "John Doe", "American");
        Book book = new Book(1, "Learning Java", author, 2022);

        libraryService.updateBook(book); // Call the service method to update

        // Verify that the updateBook method on the DAO was invoked
        verify(bookDAO).updateBook(book);
    }

    @Test
    void deleteBook() {
        // Arrange
        Author author = new Author(1, "Jane Doe", "Canadian");

        // Mock behavior for adding author
        doNothing().when(authorDAO).addAuthor(any(Author.class));
        libraryService.addAuthor(author); // Ensure the author exists

        // Ensure that the new book will also be added
        Book book = new Book(1, "Learning Java", author, 2022);

        // Simulating the add operation and hypothetical behavior of DAO
        doNothing().when(bookDAO).addBook(any(Book.class)); // Mock behavior for adding book
        libraryService.addBook(book); // Call the service method to add the book

        // Mock behavior for retrieving the book before deleting
        when(bookDAO.getBook(book.getId())).thenReturn(book); // Ensure the mocked getBook returns the added book

        // Act
        libraryService.deleteBook(book.getId()); // Call to delete the book

        // Verify that the delete method was called on the DAO
        verify(bookDAO, times(1)).deleteBook(book.getId());
    }

    @Test
    void addAuthor() {
        Author author = new Author(0, "John Doe", "American");

        // Simulating the add operation and ID generation
        doNothing().when(authorDAO).addAuthor(any(Author.class)); // No operation for the add
        when(authorDAO.getAuthor(anyInt())).thenReturn(author); // Simulating retrieval of that author after adding

        libraryService.addAuthor(author); // Call the service method

        // Verify that the author was added
        verify(authorDAO).addAuthor(author); // Check if the DAO method was called
    }

    @Test
    void getAuthor() {
        Author author = new Author(1, "John Doe", "American");
        when(authorDAO.getAuthor(1)).thenReturn(author); // Mock DAO to return the author

        Author retrievedAuthor = libraryService.getAuthor(1);
        assertEquals(author, retrievedAuthor);
    }

    @Test
    void getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "John Doe", "American"));
        authors.add(new Author(2, "Jane Smith", "British"));

        when(authorDAO.getAllAuthors()).thenReturn(authors); // Mock DAO to return the list of authors

        List<Author> retrievedAuthors = libraryService.getAllAuthors();
        assertEquals(2, retrievedAuthors.size()); // Verify the size of the authors list
        assertEquals(authors, retrievedAuthors); // Verify the actual authors returned
    }

    @Test
    void updateAuthor() {
        Author author = new Author(1, "John Doe", "American");
        libraryService.updateAuthor(author); // Call the service method to update

        // Verify that the author was updated in the DAO
        verify(authorDAO).updateAuthor(author);
    }

    @Test
    void deleteAuthor() {
        // Arrange
        Author author = new Author(1, "John Doe", "American");

        // Set up the mock to return the author with the specified ID
        when(authorDAO.getAuthor(author.getId())).thenReturn(author); // Mock to return the author

        // Act
        libraryService.addAuthor(author); // This should result in saving the author
        libraryService.deleteAuthor(author.getId()); // Call to delete the author

        // Verify that the delete method was called on the DAO
        verify(authorDAO).deleteAuthor(author.getId()); // Now it should be invoked properly
    }

    @Test
    void getBooksByAuthorName() {
        // Arrange
        Author author = new Author(1, "John Doe", "American");
        Book book1 = new Book(1, "Java for Beginners", author, 2020);
        Book book2 = new Book(2, "Advanced Java", author, 2021);

        List<Book> booksByAuthor = List.of(book1, book2);

        // Mock the DAO behavior
        when(bookDAO.getBooksByAuthorName("John Doe")).thenReturn(booksByAuthor);

        // Act
        List<Book> retrievedBooks = libraryService.getBooksByAuthorName("John Doe");

        // Assert
        assertEquals(2, retrievedBooks.size());
        assertEquals(booksByAuthor, retrievedBooks);  // Verify the actual books returned
    }

    @Test
    void getBooksByNationality() {
        // Arrange
        Author author1 = new Author(1, "Author One", "USA");
        Author author2 = new Author(2, "Author Two", "USA");

        Book book1 = new Book(1, "Book One by Author One", author1, 2020);
        Book book2 = new Book(2, "Book Two by Author Two", author2, 2021);

        List<Book> booksByNationality = List.of(book1, book2);

        // Mock the DAO behavior
        when(bookDAO.getBooksByNationality("USA")).thenReturn(booksByNationality);

        // Act
        List<Book> retrievedBooks = libraryService.getBooksByNationality("USA");

        // Assert
        assertEquals(2, retrievedBooks.size());
        assertEquals(booksByNationality, retrievedBooks); // Verify the actual books returned
    }

    @Test
    void getBooksByPublishedYear() {
        // Arrange
        Author author = new Author(1, "John Doe", "American");

        Book book1 = new Book(1, "Book From 2022", author, 2022);
        Book book2 = new Book(2, "Another Book From 2022", author, 2022);

        List<Book> booksFromYear = List.of(book1, book2);

        // Mock the DAO behavior
        when(bookDAO.getBooksByPublishedYear(2022)).thenReturn(booksFromYear);

        // Act
        List<Book> retrievedBooks = libraryService.getBooksByPublishedYear(2022);

        // Assert
        assertEquals(2, retrievedBooks.size());
        assertEquals(booksFromYear, retrievedBooks); // Verify the actual books returned
    }

    @Test
    void getBook_NotFound() {
        int bookId = 999; // Assume this ID does not exist
        when(bookDAO.getBook(bookId)).thenReturn(null); // Mock the DAO to return null

        Book book = libraryService.getBook(bookId);

        assertNull(book); // Assert that the result is null as expected
        verify(bookDAO).getBook(bookId); // Verify the DAO method was invoked
        // Verify log output for warning
        // Note: You may wish to use a logging framework that allows verification or simply check the output manually
    }

    @Test
    void getAuthor_NotFound() {
        int authorId = 999; // Assume this ID does not exist
        when(authorDAO.getAuthor(authorId)).thenReturn(null); // Mock the DAO to return null

        Author author = libraryService.getAuthor(authorId);

        assertNull(author); // Assert that the result is null as expected
        verify(authorDAO).getAuthor(authorId); // Verify the DAO method was invoked
        // Verify log output for warning
    }
}