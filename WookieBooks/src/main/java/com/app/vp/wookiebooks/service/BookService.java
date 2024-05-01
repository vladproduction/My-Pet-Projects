package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //createBook
    public Book createBook(Book book){
        return bookRepository
                .saveAndFlush(book);
    }

    //findBookById
    public Optional<Book> findBookById(Long bookId){
        return bookRepository
                .findById(bookId);
    }

    //findBookByTitle
    public Optional<Book> findBookByTitle(String title){
        return bookRepository
                .findBookByTitle(title);
    }
}
