package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.exceptions.PermissionDeniedException;
import com.app.vp.wookiebooks.exceptions.ResourceNotFoundException;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.BookRepository;
import com.app.vp.wookiebooks.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    public List<Book> findAllBooks(String authorPseudonym,
                                   String title,
                                   Double price){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = query.from(Book.class);
        query.select(bookRoot);

        List<Predicate> predicates = new ArrayList<>();

        if(authorPseudonym != null){
            Join<Book, User> authorJoin = bookRoot.join("author");
            Predicate pseudonymPredicate = criteriaBuilder.equal(authorJoin.get("authorPseudonym"), authorPseudonym);
            predicates.add(pseudonymPredicate);
        }

        if(title != null){
            Predicate titlePredicate = criteriaBuilder.equal(bookRoot.get("title"), title);
            predicates.add(titlePredicate);
        }

        if(price != null){
            Predicate pricePredicate = criteriaBuilder.equal(bookRoot.get("price"), price);
            predicates.add(pricePredicate);
        }

        Predicate [] predicatesArr = new Predicate[predicates.size()];
        Predicate[] predicatesArray = predicates.toArray(predicatesArr);
        query.where(criteriaBuilder.and(predicatesArray));

        return entityManager.createQuery(query).getResultList();

    }

    //[POST]: createBook
    public Book createBook(Book book) {
        return bookRepository
                .saveAndFlush(book);
    }

    //[GET]: findBookById
    public Optional<Book> findBookById(Long bookId) {
        return bookRepository
                .findById(bookId);
    }

    //[GET]: findBookByTitle
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository
                .findBookByTitle(title);
    }

    public void deleteBook(String title, String authorPseudonym){
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if(bookByTitle.isPresent()){
            Book book = bookByTitle.get();
            validateAuthor(authorPseudonym, book);
            bookRepository.delete(book);
        }
        else {
            throw new ResourceNotFoundException("Book with title " + title + " was not found");
        }
    }

    public Book updateBook(Book candidate, Long bookId, String authorPseudonym){
        Book book = bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException("Book with provided id: " + bookId + " is not found"));
        validateAuthor(authorPseudonym, book);
        book.setTitle(candidate.getTitle());
        book.setAuthor(candidate.getAuthor());
        book.setDescription(candidate.getDescription());
        book.setCoverImage(candidate.getCoverImage());
        book.setPrice(candidate.getPrice());
        return bookRepository.save(book);
    }

    private void validateAuthor(String authorPseudonym, Book book){
        if(isSuperAdmin()) return;

        if(!book.getAuthor().getAuthorPseudonym().equals(authorPseudonym)){
            throw new PermissionDeniedException("Not enough permissions for actions with this book");
        }
    }

    private boolean isSuperAdmin(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Optional<? extends GrantedAuthority> optional = authorities.stream().filter((role) -> Roles.SUPER_ADMIN.name().equals(role.getAuthority())).findFirst();
        return optional.isPresent();
    }

    //[GET]: findAllBooks
    public List<Book> findAllBooks() {
        return bookRepository
                .findAll();
    }

    //[GET]: findAllBooksByUserId
    public Optional<List<Book>> findAllBooksByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Book> bookList = new ArrayList<>();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Book> userBooks = bookRepository.findBooksByAuthorUserId(user.getUserId()); //using custom method
            bookList.addAll(userBooks);
        }
        return Optional.of(bookList);
    }

    //[GET]: findAllBooksByAuthorPseudonym
    public Optional<List<Book>> findAllBooksByAuthorPseudonym(String authorPseudonym) {
        Optional<User> optionalUser = userRepository.findUserByAuthorPseudonym(authorPseudonym);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Book> booksByAuthorPseudonym = bookRepository.findByAuthorPseudonym(user.getAuthorPseudonym());
            return Optional.of(booksByAuthorPseudonym);
        }
        return Optional.empty();
    }

    //[GET]: findBooksByPriceLessThan
    public Optional<List<Book>> findBooksByPriceLessThan(double price) {
        if(price > 0){
            List<Book> booksByPriceLessThan = bookRepository.findBooksByPriceLessThan(price);
            return Optional.of(booksByPriceLessThan);
        } else{
            System.err.println("Price is not valid");
            return Optional.empty();
        }
    }

    //[GET]: findBooksByMiddlePrice
    public Optional<List<Book>> findBooksByMiddlePrice(double minPrice, double maxPrice) {
        if(minPrice > 0 & maxPrice > minPrice){
            List<Book> booksByMiddlePrice = bookRepository.findBooksByMiddlePrice(minPrice, maxPrice);
            return Optional.of(booksByMiddlePrice);
        } else {
            System.err.println("Price is not valid");
            return Optional.empty();
        }
    }

    //[GET]: findBooksByHighPrice
    public Optional<List<Book>> findBooksByHighPrice(double minPrice) {
        if(minPrice > 0){
            List<Book> booksByHighPrice = bookRepository.findBooksByHighPrice(minPrice);
            return Optional.of(booksByHighPrice);
        } else {
            System.err.println("Price is not valid");
            return Optional.empty();
        }
    }

    //[GET]: findBookAndUserByUserId
    public Optional<List<Book>> findBookAndUserByUserId(Long userId){
        List<Book> bookList = bookRepository.findBookAndUserByUserId(userId);
        if(bookList != null) return Optional.of(bookList);
        return Optional.empty();
    }
}
