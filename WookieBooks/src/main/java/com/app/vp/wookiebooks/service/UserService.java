package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.BookRepository;
import com.app.vp.wookiebooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //createUser
    public User createUser(User user){
        return userRepository
                .saveAndFlush(user);
    }

    //getUserById
    public Optional<User> getUserById(Long userId){
        return userRepository
                .findById(userId);
    }

    //findUserByAuthorPseudonym
    public Optional<User> findUserByAuthorPseudonym(String authorPseudonym){
        return userRepository
                .findUserByAuthorPseudonym(authorPseudonym);
    }

    //updateAuthorPseudonym
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

    //deleteUserById
    public int deleteUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            userRepository
                    .deleteById(userId);
            return 0;
        }
        return -1;
    }

    //findAllUsers
    public List<User> findAllUsers() {
        return userRepository
                .findAll();
    }

    //findUserByBookTitle
    public Optional<User> findUserByBookTitle(String title) {
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if(bookByTitle.isPresent()){
            Book book = bookByTitle.get();
            User user = book.getAuthor();
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
