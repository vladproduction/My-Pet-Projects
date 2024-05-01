package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BookServiceInterface {

    Book createBook(Book book);
    Optional<Book> findBookById(Long bookId);
    Optional<Book> findBookByTitle(String title);


}
