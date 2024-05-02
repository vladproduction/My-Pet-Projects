package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.BookRepository;
import com.app.vp.wookiebooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is a Spring Service responsible for business logic related to User.
 * It interacts with the database using:
 *  -the injected UserRepository (JpaRepository<User, Long>) interface;
 *  -the injected BookRepository (JpaRepository<Book, Long>) interface;
 * This service provides methods for:
 *  -CRUD operations (Create, Read, Update, Delete) on User model.
 *  -Additional business logic specific to User management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    //[POST]: createUser
    public User createUser(User user){
        return userRepository
                .saveAndFlush(user);
    }

    //[GET]: getUserById
    public Optional<User> getUserById(Long userId){
        return userRepository
                .findById(userId);
    }

    //[GET]: findUserByAuthorPseudonym
    public Optional<User> findUserByAuthorPseudonym(String authorPseudonym){
        return userRepository
                .findUserByAuthorPseudonym(authorPseudonym);
    }

    //[UPDATE]: updateAuthorPseudonym
    public Optional<User> updateAuthorPseudonym(String authorPseudonym, String newPseudonym){
        Optional<User> optionalUser = userRepository.findUserByAuthorPseudonym(authorPseudonym);
        if (optionalUser.isPresent()){
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
        if(optionalUser.isPresent()){
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
        if(bookByTitle.isPresent()){
            Book book = bookByTitle.get();
            User user = book.getAuthor();
            return Optional.of(user);
        }
        return Optional.empty();
    }

    //[DELETE]: deleteBookByUser
    public List<Book> deleteBookByUser(Long userId, Long bookId){
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Book> remainingBooks = new ArrayList<>();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //retrieve all user`s books
            List<Book> userBooks = bookRepository.findBookAndUserByUserId(user.getUserId());
            //searching the book that need to delete
            Book bookHasToBeDeleted = userBooks.stream()
                    .filter(book -> book.getBookId().equals(bookId))
                    .findFirst().orElse(null);
            if(bookHasToBeDeleted != null){
                //remove the book from user`s books and repository
                userBooks.remove(bookHasToBeDeleted);
                bookRepository.delete(bookHasToBeDeleted);
            }
            //update remaining books
            remainingBooks.addAll(userBooks);
        }
        return remainingBooks;
    }
}
