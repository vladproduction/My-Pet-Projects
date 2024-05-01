package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.exceptions.UserNotFoundException;
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
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    //create user
    public User createUser(User user){
        return userRepository
                .saveAndFlush(user);
    }

    //get user by id
    public Optional<User> getUserById(Long userId){
        return userRepository
                .findById(userId);
    }

    //get user by authorPseudonym
    public Optional<User> findUserByAuthorPseudonym(String authorPseudonym){
        return userRepository
                .findUserByAuthorPseudonym(authorPseudonym);
    }

    //update user pseudonym
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

    //delete user by userId
    @Override
    public void deleteUserByUserId(Long userId) throws UserNotFoundException {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()){
            User user = byId.get();
            userRepository
                    .deleteById(user.getUserId());
        }
    }


    //todo un-publish a book (DELETE) - owner of the book could delete it


}
