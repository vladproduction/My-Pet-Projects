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


import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Spring REST controller handles incoming HTTP requests to RestApiController.
 * It provides endpoints for Book (postman):
 * -[POST]:     createBook;
 * -[GET]:      findBookById;
 * -[GET]:      findBookByTitle;
 * -[GET]:      findAllBooks;
 * -[GET]:      findAllBooksByUserId;
 * -[GET]:      findAllBooksByAuthorPseudonym;
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

    //[GET]: findAllBooks
    @GetMapping("/books/findAllBooks")
    public ResponseEntity<List<BookDto>> findAllBooks(){
        List<Book> books = bookService.findAllBooks();
        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(books);
        return ok(bookDtoList);
    }

    //[GET]: findAllBooksByUserId
    @GetMapping("/books/findAllBooksByUserId/{userId}")
    public ResponseEntity<List<BookDto>> findAllBooksByUserId(
            @PathVariable Long userId){
        Optional<List<Book>> optionalBooks = bookService.findAllBooksByUserId(userId);
        if(optionalBooks.isPresent()){
            List<Book> bookList = optionalBooks.get();
            List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
            return ok(bookDtoList);
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findAllBooksByAuthorPseudonym
    @GetMapping("/books/findAllBooksByAuthorPseudonym")
    public ResponseEntity<Optional<List<BookDto>>> findAllBooksByAuthorPseudonym(
            @RequestParam String authorPseudonym){
        Optional<List<Book>> optionalBooks = bookService.findAllBooksByAuthorPseudonym(authorPseudonym);
        if(optionalBooks.isPresent()){
            List<Book> bookList = optionalBooks.get();
            List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
            return ok(Optional.of(bookDtoList));
        }
        return ResponseEntity.notFound().build();
    }


}


/**
 *
 * -Allows only GET (List/Detail) operations
 * -Make the List resource searchable with query parameters
 * -update Book as owner
 * -delete Book  as owner
 * -find Books by (special parameters: price + join author):
 *      low price(< 10),
 *      middle price(>= 10 < 50),
 *      high price(>=50)
 *
 * */
