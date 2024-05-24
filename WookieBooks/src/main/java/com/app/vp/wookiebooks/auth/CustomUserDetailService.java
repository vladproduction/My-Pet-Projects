package com.app.vp.wookiebooks.auth;

import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This service responsible for such mechanism:
 * -we send by Postman username to the db and check if we have this user at all;
 * -if we got it (User) object --> then
 * -we got by this service UserDetails data, that were going to use in SecurityConfig;
 * */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method return standard UserDetails data for existing user from db;
     * -logic is: to find at 1-step; and get details by this user at 2-step for springframework.User ;
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getAuthorPseudonym(), //private String authorPseudonym from model.User
                user.getAuthorPassword(), //private String authorPassword from model.User
                List.of(new SimpleGrantedAuthority(user.getRoles().name()))); //array of permissions (which roles user has)
    }

    /**
     * Helper method: just to find user by authorPseudonym
     */
    private User findUser(String authorPseudonym){
        Optional<User> optionalUser = userRepository.findUserByAuthorPseudonym(authorPseudonym);
        return optionalUser.orElse(null);
    }


}
