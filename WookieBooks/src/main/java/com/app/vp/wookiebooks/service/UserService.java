package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.BookRepository;
import com.app.vp.wookiebooks.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is a Spring Service responsible for business logic related to User.
 * It interacts with the database using:
 * -the injected UserRepository (JpaRepository<User, Long>) interface;
 * -the injected BookRepository (JpaRepository<Book, Long>) interface;
 * This service provides methods for:
 * -CRUD operations (Create, Read, Update, Delete) on User model.
 * -Additional business logic specific to User management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Optional<User> optionalUser = findUserByRole(Roles.SUPER_ADMIN);
        if (optionalUser.isEmpty()) {
            User userDefault = new User();
            userDefault.setRoles(Roles.SUPER_ADMIN);
            userDefault.setAuthorPseudonym("Lohgarra");
            userDefault.setAuthorPassword("defaultPassword");
            createUser(userDefault);
        }

    }

    public Optional<User> findUserByRole(Roles roles) {
        return userRepository.findUserByRoles(roles);
    }


    //[POST]: createUser
    public User createUser(User user) {
        String encoded = passwordEncoder.encode(user.getAuthorPassword());
        user.setAuthorPassword(encoded);
        return userRepository
                .save(user);
    }

    //[GET]: getUserById
    public Optional<User> getUserById(Long userId) {
        return userRepository
                .findById(userId);
    }

    //[GET]: findUserByAuthorPseudonym
    public Optional<User> findUserByAuthorPseudonym(String authorPseudonym) {
        return userRepository
                .findUserByAuthorPseudonym(authorPseudonym);
    }

    //[PUT]: updateAuthorPseudonym
    public Optional<User> updateAuthorPseudonym(String authorPseudonym, String newPseudonym) {
        Optional<User> optionalUser = userRepository.findUserByAuthorPseudonym(authorPseudonym);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAuthorPseudonym(newPseudonym);
            return Optional.of(userRepository
                    .saveAndFlush(user));
        }
        return Optional.empty();
    }

    //[DELETE]: deleteUserById
    public int deleteUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository
                    .deleteById(userId);
            return 0;
        }
        return -1;
    }

    //[GET]: findAllUsers
    public List<User> findAllUsers() {
        return userRepository
                .findAll();
    }

    //[GET]: findUserByBookTitle
    public Optional<User> findUserByBookTitle(String title) {
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if (bookByTitle.isPresent()) {
            Book book = bookByTitle.get();
            User user = book.getAuthor();
            return Optional.of(user);
        }
        return Optional.empty();
    }

    //[DELETE]: deleteBookByUser
    public List<Book> deleteBookByUser(Long userId, Long bookId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Book> remainingBooks = new ArrayList<>();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            //retrieve all user`s books
            List<Book> userBooks = bookRepository.findBookAndUserByUserId(user.getUserId());
            //searching the book that need to delete
            Book bookHasToBeDeleted = userBooks.stream()
                    .filter(book -> book.getBookId().equals(bookId))
                    .findFirst().orElse(null);
            if (bookHasToBeDeleted != null) {
                //remove the book from user`s books and repository
                userBooks.remove(bookHasToBeDeleted);
                bookRepository.delete(bookHasToBeDeleted);
            }
            //update remaining books
            remainingBooks.addAll(userBooks);
        }
        return remainingBooks;
    }

    //[PATCH]: updateBookByUser
    public List<Book> updateBookByUser(Long userId, Long bookId, Book candidate) {
        //find user
        Optional<User> optionalUser = userRepository.findById(userId);
        //list with updated book
        List<Book> updatedBooks = new ArrayList<>();
        if (optionalUser.isPresent()) {
            //get user
            User user = optionalUser.get();
            // Retrieve all user's books
            List<Book> userBooks = bookRepository.findBookAndUserByUserId(user.getUserId());
            // Find the book to be updated (filter by ID)
            Book bookToUpdate = userBooks.stream()
                    .filter(book -> book.getBookId().equals(bookId))
                    .findFirst().orElse(null);
            if (bookToUpdate != null) {
                // Update book properties with data from Book candidate
                // Update properties as needed
                bookToUpdate.setTitle(candidate.getTitle());
                bookToUpdate.setDescription(candidate.getDescription());
                bookToUpdate.setCoverImage(candidate.getCoverImage());
                bookToUpdate.setPrice(candidate.getPrice());
                bookRepository.save(bookToUpdate); // Save the updated book
                updatedBooks.add(bookToUpdate);
            }
        }
        return updatedBooks;
    }

}
