package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements BookServiceInterface{

    @Autowired
    private BookRepository bookRepository;

    //create book
    public Book createBook(Book book){
        return bookRepository
                .saveAndFlush(book);
    }

    //find book by bookId
    public Optional<Book> findBookById(Long bookId){
        return bookRepository
                .findById(bookId);
    }

    //find book by title
    public Optional<Book> findBookByTitle(String title){
        return bookRepository
                .findBookByTitle(title);
    }

}
