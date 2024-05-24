package com.app.vp.wookiebooks.repository;

import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * This interface extends the `JpaRepository` provided by Spring Data JPA.
 * It provides basic CRUD operations (Create, Read, Update, Delete) for User;
 * Custom methods provides to.
 * Common database operations based on type (User) and its identifier (Long).
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByAuthorPseudonym(String authorPseudonym);

    Optional<User> findUserByRoles(Roles roles);
}
