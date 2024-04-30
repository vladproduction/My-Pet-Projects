package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is a Spring Service responsible for business logic related to User.
 * It interacts with the database using the injected UserRepository (JpaRepository<User, Long>) interface.
 * This service provides methods for:
 *  -CRUD operations (Create, Read, Update, Delete) on User model.
 *  -Additional business logic specific to User management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //typical CRUD operations for this resource
    //create user
    public User createUser(User user){
        return userRepository.save(user);
    }

    //get user by id
    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }

    //get user by authorPseudonym
    Optional<User> findByAuthorPseudonym(String authorPseudonym){
        return userRepository.findByAuthorPseudonym(authorPseudonym);
    }

    //update user
    //todo
    //delete user
    //todo
    //un-publish a book (DELETE) - owner of the book could delete it
    //todo


}
