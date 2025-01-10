package com.vladproduction.library.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Author model testing")
class AuthorTest {

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author(1, "John", "USA");
    }

    @AfterEach
    void tearDown() {
        author = null;
    }

    @Test
    public void testAuthorCreation() {
        Author author = new Author(1, "John Doe", "American");
        assertEquals(1, author.getId());
        assertEquals("John Doe", author.getName());
        assertEquals("American", author.getNationality());
        assertTrue(author.getBooks().isEmpty(), "Author should have no books initially.");
    }

    @Test
    void getId() {
        int id = author.getId();
        assertEquals(1, id);
    }

    @Test
    void setId() {
        author.setId(23);
        int id = author.getId();
        assertEquals(23, id);
    }

    @Test
    void getName() {
        String name = author.getName();
        assertSame("John", name);
    }

    @Test
    void setName() {
        author.setName("Johanna");
        String name = author.getName();
        assertSame("Johanna", name);
    }

    @Test
    void getNationality() {
        String nationality = author.getNationality();
        assertEquals("USA", nationality);
    }

    @Test
    void setNationality() {
        author.setNationality("Brazil");
        String nationality = author.getNationality();
        assertEquals("Brazil", nationality);
    }

    @Test
    void getBooks() {
        Book book = new Book(1, "Java", author, 2023);
        author.addBook(book);
        List<Book> books = author.getBooks();
        assertEquals(1, books.size());
    }

    @Test
    void addBook() {
        Book book = new Book(1, "Java", author, 2023);
        author.addBook(book);
        List<Book> books = author.getBooks();
        for (Book b : books) {
            assertSame(b, book);
        }
    }

    @Test
    public void testAdd2BooksPositive(){
        Author author = new Author(1, "Jane Smith", "Canadian");
        Book book = new Book(1, "Book Title1", author, 2022);
        Book book2 = new Book(2, "Book Title2", author, 2023);
        author.addBook(book);
        author.addBook(book2);

        assertEquals(2, author.getBooks().size(), "Author should have 2 book after adding.");
        assertEquals(book, author.getBooks().get(0), "The book added should match the book in the author's list.");
        assertEquals(book2, author.getBooks().get(1), "The book added should match the book in the author's list.");
    }

    @Test
    void testToString() {
        Author author = new Author(1, "Mark Twain", "American");
        assertEquals("Author [id: 1, name: Mark Twain, nationality: American]", author.toString());
    }
}