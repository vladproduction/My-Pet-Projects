package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(Long id);

}
