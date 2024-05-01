package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BookServiceInterface{

    @Autowired
    private BookRepository bookRepository;

    //create book
    public Book createBook(Book book){
        return bookRepository
                .saveAndFlush(book);
    }

}
