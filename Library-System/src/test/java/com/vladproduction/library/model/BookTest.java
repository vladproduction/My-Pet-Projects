package com.vladproduction.library.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book model testing")
class BookTest {

    private Author author1;
    private Author author2;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    void setUp() {
        author1 = new Author(1, "Author1", "USA");
        author2 = new Author(2, "Author2", "Brazil");
        book1 = new Book(1, "Java1", author1, 2021);
        book2 = new Book(2, "Java2", author1, 2022);
        book3 = new Book(3, "Java3", author2, 2023);
        book4 = new Book(4, "Java4", author2, 2024);
    }

    @AfterEach
    void tearDown() {
        author1 = null;
        author2 = null;
        book1 = null;
        book2 = null;
        book3 = null;
        book4 = null;
    }

    @Test
    public void testBookCreation() {
        Author author = new Author(1, "Charles Dickens", "British");
        Book book = new Book(1, "Great Expectations", author, 1861);

        assertEquals(1, book.getId());
        assertEquals("Great Expectations", book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(1861, book.getPublishedYear());
    }

    @Test
    void getId() {
        assertEquals(1, book1.getId());
    }

    @Test
    void setId() {
        book1.setId(22);
        assertEquals(22, book1.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Java1", book1.getTitle());
    }

    @Test
    void setTitle() {
        book1.setTitle("Java12");
        assertEquals("Java12", book1.getTitle());
    }

    @Test
    void getAuthor() {
        Author author = book2.getAuthor();
        assertEquals(author1.getName(), author.getName());
    }

    @Test
    void setAuthor() {
        book2.setAuthor(author2);
        Author author = book2.getAuthor();
        assertEquals(author2.getName(), author.getName());
        assertNotEquals(author1.getName(), author.getName());
    }

    @Test
    void getPublishedYear() {
        int publishedYear = book4.getPublishedYear();
        assertEquals(2024, publishedYear);
    }

    @Test
    void setPublishedYear() {
        book1.setPublishedYear(2024);
        int publishedYear = book1.getPublishedYear();
        assertEquals(2024, publishedYear);
    }

    @Test
    void testToString() {
        String bookA = "Book [id: 2, title: Java2, author: Author1, publishedYear: 2022]";
        String bookB = "Book [id: 3, title: Java3, author: Author2, publishedYear: 2023]";
        assertEquals(bookA, book2.toString());
        assertEquals(bookB, book3.toString());
    }

    @Test
    public void testToStringWithAuthor() {
        Author author = new Author(1, "Ernest Hemingway", "American");
        Book book = new Book(1, "The Old Man and the Sea", author, 1952);
        assertEquals("Book [id: 1, title: The Old Man and the Sea, author: Ernest Hemingway, publishedYear: 1952]", book.toString());
    }
}