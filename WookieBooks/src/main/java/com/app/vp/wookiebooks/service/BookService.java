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

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    //createBook
    public Book createBook(Book book) {
        return bookRepository
                .saveAndFlush(book);
    }

    //findBookById
    public Optional<Book> findBookById(Long bookId) {
        return bookRepository
                .findById(bookId);
    }

    //findBookByTitle
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository
                .findBookByTitle(title);
    }

    //findAllBooks
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    //findAllBooksByUserId
    public Optional<List<Book>> findAllBooksByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Book> bookList = new ArrayList<>();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
//            Optional<Book> optionalBook = bookRepository.findById(user.getUserId());
//            if (optionalBook.isPresent()){
//                Book book = optionalBook.get();
//                bookList.add(book);
//            }
            //instead of fetching a single book by userId, fetch all books associated with userId
            List<Book> userBooks = bookRepository.findBooksByAuthorUserId(user.getUserId()); //using custom method
            bookList.addAll(userBooks);
        }
        return Optional.of(bookList);
    }

    //findAllBooksByAuthorPseudonym
    public Optional<List<Book>> findAllBooksByAuthorPseudonym(String authorPseudonym) {
        Optional<User> optionalUser = userRepository.findUserByAuthorPseudonym(authorPseudonym);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Book> booksByAuthorPseudonym = bookRepository.findByAuthorPseudonym(user.getAuthorPseudonym());
            return Optional.of(booksByAuthorPseudonym);
        }
        return Optional.empty();
    }

}
