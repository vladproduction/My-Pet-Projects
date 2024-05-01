package com.app.vp.wookiebooks.controller;


import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.BookMapper;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.BookService;
import com.app.vp.wookiebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Spring REST controller handles incoming HTTP requests to RestApiController.
 * It provides endpoints for Book (postman):
 * +[POST]: createBook;
 * +[GET]: findBookById;
 * +[GET]: findBookByTitle;
 * Interacts with the following services:
 * -UserService;
 * -BookService;
 * */
@RestController
@RequestMapping("/api/wookie_books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    //[POST]: createBook
    @PostMapping("/books/createBook")
    public ResponseEntity<BookDto> createBook(
            @RequestBody BookDto bookDto){
        Book book = BookMapper.mapToBook(bookDto);
        UserDto bookDtoAuthor = bookDto.getAuthor();
        if(bookDtoAuthor != null){
            String authorAuthorPseudonym = bookDtoAuthor.getAuthorPseudonym();
            if(authorAuthorPseudonym != null){
                Optional<User> userOptional = userService.findUserByAuthorPseudonym(authorAuthorPseudonym);
                userOptional.ifPresent(book::setAuthor);
            }
        }
        Book savedBook = bookService.createBook(book);
        BookDto createdBookDto = BookMapper.mapToBookDto(savedBook);
        return ok(createdBookDto);
    }

    //[GET]: findBookById
    @GetMapping("/books/findBookById/{bookId}")
    public ResponseEntity<Optional<BookDto>> findBookById(
            @PathVariable Long bookId){
        Optional<Book> bookOptional = bookService.findBookById(bookId);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BookDto bookDto = BookMapper.mapToBookDto(book);
            return ok(Optional.of(bookDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findBookByTitle
    @GetMapping("/books/findBookByTitle")
    public ResponseEntity<Optional<BookDto>> findBookByTitle(
            @RequestParam String title){
        Optional<Book> bookOptional = bookService.findBookByTitle(title);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BookDto bookDto = BookMapper.mapToBookDto(book);
            return ok(Optional.of(bookDto));
        }
        return ResponseEntity.notFound().build();
    }

}


/**
 * -update Book as owner
 * -delete Book  as owner
 * -find all Books
 * -find all Books by author
 * -find Books by (special parameters: price + join author):
 *      low price(< 10),
 *      middle price(>= 10 < 50),
 *      high price(>=50)
 * */
