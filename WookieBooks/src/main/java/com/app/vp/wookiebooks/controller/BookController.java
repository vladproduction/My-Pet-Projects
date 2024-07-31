package com.app.vp.wookiebooks.controller;


import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.BookMapper;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.BookService;
import com.app.vp.wookiebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/wookie_books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    //[POST]: createBook
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(
            @RequestBody BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        UserDto bookDtoAuthor = bookDto.getAuthor();
        defineAuthor(book, bookDtoAuthor);
        Book savedBook = bookService.createBook(book);
        return BookMapper.mapToBookDto(savedBook);
    }

    //[PUT]: updateBook
    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> updateBook(
            @RequestBody BookDto bookDto, @PathVariable Long bookId) {
        String authorPseudonym = SecurityContextHolder.getContext().getAuthentication().getName();
        Book book = BookMapper.mapToBook(bookDto);
        UserDto bookDtoAuthor = bookDto.getAuthor();
        defineAuthor(book, bookDtoAuthor);
        Book updatedBook = bookService.updateBook(book, bookId, authorPseudonym);
        BookDto createdBookDto = BookMapper.mapToBookDto(updatedBook);
        return ok(createdBookDto);
    }

    private void defineAuthor(Book book, UserDto bookDtoAuthor) {
        if (bookDtoAuthor != null) {
            String authorAuthorPseudonym = bookDtoAuthor.getAuthorPseudonym();
            if (authorAuthorPseudonym != null) {
                Optional<User> userOptional = userService.findUserByAuthorPseudonym(authorAuthorPseudonym);
                userOptional.ifPresent(book::setAuthor);
            }
        }
    }

    //[GET]: findBookById
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findBookById(
            @PathVariable Long bookId) {
        Optional<Book> bookOptional = bookService.findBookById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookDto bookDto = BookMapper.mapToBookDto(book);
            return ok(bookDto);
        }
        return ResponseEntity.notFound().build();
    }


    //[GET]: findAllBooks
    @GetMapping
    public ResponseEntity<List<BookDto>> findAllBooks(@RequestParam(required = false) String authorPseudonym,
                                                      @RequestParam(required = false) String title,
                                                      @RequestParam(required = false) Double price) {
        List<Book> books = bookService.findAllBooks(authorPseudonym, title, price);
        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(books);
        return ok(bookDtoList);
    }

    //[DELETE]: deleteBook
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@RequestParam String title) {
        String authorPseudonym = SecurityContextHolder.getContext().getAuthentication().getName();
        bookService.deleteBook(title, authorPseudonym);
    }

}

