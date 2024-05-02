package com.app.vp.wookiebooks.repository;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface extends the `JpaRepository` provided by Spring Data JPA.
 * It provides basic CRUD operations (Create, Read, Update, Delete) for Book;
 * Custom methods provides to.
 * Common database operations based on type (Book) and its identifier (Long).
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    //custom methods:

    //[GET]: findAllBooksByUserId
    @Query("select b from Book b where b.author.userId=?1")
    List<Book> findBooksByAuthorUserId(Long userId);

    //[GET]: findAllBooksByAuthorPseudonym
    @Query("select b from Book b join User u on b.author.userId = u.userId WHERE u.authorPseudonym = :authorPseudonym")
    List<Book> findByAuthorPseudonym(@Param("authorPseudonym") String authorPseudonym);

    //[GET]: findBooksByPriceLessThan
    @Query("SELECT b FROM Book b WHERE b.price < :price")
    List<Book> findBooksByPriceLessThan(@Param("price") double price);

}
