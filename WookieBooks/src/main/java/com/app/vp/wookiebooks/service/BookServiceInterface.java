package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookServiceInterface {

    //methods from repository
    Book createBook(Book book);
}
