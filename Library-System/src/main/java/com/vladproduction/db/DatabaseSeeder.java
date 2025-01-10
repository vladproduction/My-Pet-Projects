package com.vladproduction.db;

import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;
import com.vladproduction.library.service.LibraryService;

import java.util.Arrays;
import java.util.List;

public class DatabaseSeeder {
    private LibraryService libraryService;

    public DatabaseSeeder(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void seedData() {
        // Check if authors already exist
        if (authorsExist()) {
            System.out.println("Authors already exist in the database. Skipping seeding.");
            return; // Skip seeding if authors already exist
        }
        seedAuthors(); // Seed authors first
        seedBooks();   // Seed books after authors
    }

    private boolean authorsExist() {
        List<Author> authors = libraryService.getAllAuthors(); // Use the service to check authors
        return !authors.isEmpty(); // Return true if list is not empty
    }

    private void seedAuthors() {
        List<Author> authors = createAuthors();
        for (Author author : authors) {
            libraryService.addAuthor(author); // Add each author to the database
            System.out.println("Added author: " + author.getName());
        }
    }

    private void seedBooks() {
        // Retrieve authors to associate books
        List<Author> authors = libraryService.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors found to associate with books.");
            return; // Exit if no authors are available
        }

        for (Author author : authors) {
            for (int i = 1; i <= 3; i++) { // Example: each author has 3 books
                Book book = new Book(0, author.getName() + "'s Book " + i, author, 2000 + i); // Varying year for uniqueness
                libraryService.addBook(book);
                author.addBook(book); // Maintain the author's book list as well
                System.out.println("Added book: " + book.getTitle());
            }
        }
    }

    private List<Author> createAuthors() {
        return List.of(
                new Author(0, "Author One", "USA"),
                new Author(0, "Author Two", "UK"),
                new Author(0, "Author Three", "Canada"),
                new Author(0, "Author Four", "Australia"),
                new Author(0, "Author Five", "India"),
                new Author(0, "Author Six", "Germany"),
                new Author(0, "Author Seven", "France"),
                new Author(0, "Author Eight", "Italy"),
                new Author(0, "Author Nine", "Spain"),
                new Author(0, "Author Ten", "Brazil")
        );
    }
}
