package com.vladproduction.library.app;

import com.vladproduction.db.DatabaseConnection;
import com.vladproduction.db.DatabaseInitializer;
import com.vladproduction.db.DatabaseSeeder;
import com.vladproduction.library.model.Author;
import com.vladproduction.library.model.Book;
import com.vladproduction.library.service.LibraryService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    //start position: check connectivity and starting app;
    /*public static void main(String[] args) {
        System.out.println("Hello Library App");
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Initialize the database
            DatabaseInitializer.initializeDatabase(connection);
            System.out.println("Now we can use our LibraryService here...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //implementing users interaction to the app:
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Ensure the database and tables are initialized
            DatabaseInitializer.initializeDatabase(connection);

            // Create an instance of LibraryService
            LibraryService libraryService = new LibraryService(connection);

            // Seed the database with initial data
            DatabaseSeeder seeder = new DatabaseSeeder(libraryService);
            seeder.seedData();

            // Proceed with the user menu for further actions
            Scanner scanner = new Scanner(System.in);
            String choice;

            do {
                // Display the menu
                System.out.println("Library Management System");
                System.out.println("1. Add a book");
                System.out.println("2. Retrieve a book by ID");
                System.out.println("3. List all books");
                System.out.println("4. Update a book's information");
                System.out.println("5. Delete a book");
                System.out.println("6. Add an author");
                System.out.println("7. Retrieve an author by ID");
                System.out.println("8. List all authors");
                System.out.println("9. Update an author's information");
                System.out.println("10. Delete an author");
                System.out.println("11. Get all books by author name");
                System.out.println("12. Get all books by nationality");
                System.out.println("13. Get all books by published year");
                System.out.println("14. Exit the application");
                System.out.print("Enter your choice: ");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1": addBook(libraryService, scanner);
                        break;
                    case "2": retrieveBookById(libraryService, scanner);
                        break;
                    case "3": listAllBooks(libraryService);
                        break;
                    case "4": updateBook(libraryService, scanner);
                        break;
                    case "5": deleteBook(libraryService, scanner);
                        break;
                    case "6": addAuthor(libraryService, scanner);
                        break;
                    case "7": retrieveAuthorById(libraryService, scanner);
                        break;
                    case "8": listAllAuthors(libraryService);
                        break;
                    case "9": updateAuthor(libraryService, scanner);
                        break;
                    case "10": deleteAuthor(libraryService, scanner);
                        break;
                    case "11": getBooksByAuthorName(libraryService, scanner);
                        break;
                    case "12": getBooksByNationality(libraryService, scanner);
                        break;
                    case "13": getBooksByPublishedYear(libraryService, scanner);
                        break;
                    case "14": System.out.println("Exiting the application...");
                        break;
                    default: System.out.println("Invalid choice! Please try again.");
                }
            } while (!choice.equals("14"));

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //static methods to actions cases:

    private static void addBook(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book published year: ");
        int publishedYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter author's ID (to link with this book): ");
        int authorId = Integer.parseInt(scanner.nextLine());

        // Check if the author exists
        Author author = libraryService.getAuthor(authorId);
        if (author != null) {
            // Author exists, proceed to add the book
            Book newBook = new Book(0, title, author, publishedYear); // Placeholder for ID will be auto-generated
            libraryService.addBook(newBook);
            author.addBook(newBook);  // Adding book to author's list to maintain the association
            System.out.println("Book added successfully: " + title);
        } else {
            // Author doesn't exist
            System.out.println("No author found with ID: " + authorId);
            System.out.print("You can choose an existing author or create a new one. ");
            System.out.println("Please select an existing author ID via option #8 or create a new author via option #6.");
        }
    }

    private static void retrieveBookById(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        // Get the book from the library service
        Book book = libraryService.getBook(bookId);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    private static void listAllBooks(LibraryService libraryService) {
        List<Book> books = libraryService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("List of Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void updateBook(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter book ID to update: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        // Retrieve the existing book
        Book book = libraryService.getBook(bookId);
        if (book != null) {
            System.out.println("Current Book Details: " + book);

            System.out.print("Enter new book title (leave blank to keep current): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                book.setTitle(newTitle);
            }

            System.out.print("Enter new author's ID (leave blank to keep current): ");
            String newAuthorIdInput = scanner.nextLine();
            if (!newAuthorIdInput.isEmpty()) {
                int newAuthorId = Integer.parseInt(newAuthorIdInput);
                Author newAuthor = libraryService.getAuthor(newAuthorId);
                if (newAuthor != null) {
                    book.setAuthor(newAuthor); // Update the author for the book
                } else {
                    System.out.println("No author found with ID: " + newAuthorId);
                }
            }

            System.out.print("Enter new published year (leave blank to keep current): ");
            String newPublishedYearInput = scanner.nextLine();
            if (!newPublishedYearInput.isEmpty()) {
                int newPublishedYear = Integer.parseInt(newPublishedYearInput);
                book.setPublishedYear(newPublishedYear);
            }

            // Update the book in the database
            libraryService.updateBook(book);
            System.out.println("Book updated successfully: " + book);
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    private static void deleteBook(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        // Retrieve the existing book to check if it exists
        Book book = libraryService.getBook(bookId);
        if (book != null) {
            // Prompt for confirmation before deletion
            System.out.print("Are you sure you want to delete the book: " + book.getTitle() + "? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                libraryService.deleteBook(bookId); // Delete the book using the service
                System.out.println("Book deleted successfully: " + book.getTitle());
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    private static void addAuthor(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter author's name: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter author's nationality: ");
        String nationality = scanner.nextLine();

        // Create a new author
        Author newAuthor = new Author(0, authorName, nationality); // ID will be auto-generated
        libraryService.addAuthor(newAuthor); // Add the author to the library
        System.out.println("Author added successfully: " + authorName);
    }

    private static void retrieveAuthorById(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter author's ID: ");
        int authorId = Integer.parseInt(scanner.nextLine());

        Author author = libraryService.getAuthor(authorId);
        if (author != null) {
            System.out.println("Author found: " + author);
        } else {
            System.out.println("No author found with ID: " + authorId);
        }
    }

    private static void listAllAuthors(LibraryService libraryService) {
        List<Author> authors = libraryService.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            System.out.println("List of Authors:");
            for (Author author : authors) {
                System.out.println(author);
            }
        }
    }

    private static void updateAuthor(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter author's ID to update: ");
        int authorId = Integer.parseInt(scanner.nextLine());

        // Retrieve the existing author
        Author author = libraryService.getAuthor(authorId);
        if (author != null) {
            System.out.println("Current Author Details: " + author);

            System.out.print("Enter new author's name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                author.setName(newName);
            }

            System.out.print("Enter new author's nationality (leave blank to keep current): ");
            String newNationality = scanner.nextLine();
            if (!newNationality.isEmpty()) {
                author.setNationality(newNationality);
            }

            libraryService.updateAuthor(author); // Update the author in the database
            System.out.println("Author updated successfully: " + author);
        } else {
            System.out.println("No author found with ID: " + authorId);
        }
    }

    private static void deleteAuthor(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter author's ID to delete: ");
        int authorId = Integer.parseInt(scanner.nextLine());

        // Retrieve the existing author
        Author author = libraryService.getAuthor(authorId);
        if (author != null) {
            // Prompt for confirmation before deletion
            System.out.print("Are you sure you want to delete the author: " + author.getName() + "? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                libraryService.deleteAuthor(authorId); // Delete the author using the service
                System.out.println("Author deleted successfully: " + author.getName());
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("No author found with ID: " + authorId);
        }
    }

    private static void getBooksByAuthorName(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter author's name: ");
        String authorName = scanner.nextLine();

        // Retrieve the list of books by author name
        List<Book> books = libraryService.getBooksByAuthorName(authorName);
        if (books.isEmpty()) {
            System.out.println("No books found for author: " + authorName);
        } else {
            System.out.println("List of Books by " + authorName + ":");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void getBooksByNationality(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();

        // Retrieve the list of books by nationality
        List<Book> books = libraryService.getBooksByNationality(nationality);
        if (books.isEmpty()) {
            System.out.println("No books found for authors of nationality: " + nationality);
        } else {
            System.out.println("List of Books by authors of nationality " + nationality + ":");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void getBooksByPublishedYear(LibraryService libraryService, Scanner scanner) {
        System.out.print("Enter published year: ");
        int publishedYear = Integer.parseInt(scanner.nextLine());

        // Retrieve the list of books by published year
        List<Book> books = libraryService.getBooksByPublishedYear(publishedYear);
        if (books.isEmpty()) {
            System.out.println("No books found for the year: " + publishedYear);
        } else {
            System.out.println("List of Books published in " + publishedYear + ":");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

}
