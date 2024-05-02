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
 * -Allows only GET (List/Detail) operations
 * It provides endpoints for Book (postman):
 * -[POST]:     createBook;
 * -[GET]:      findBookById;
 * -[GET]:      findBookByTitle;
 * -[GET]:      findAllBooks;
 * -[GET]:      findAllBooksByUserId;
 * -[GET]:      findAllBooksByAuthorPseudonym;
 * -[GET]:      findBooksByPriceLessThan;
 * -[GET]:      findBooksByMiddlePrice;
 * -[GET]:      findBooksByHighPrice;
 * -[GET]:      findBookAndUserByUserId
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

    //[GET]: findBooksByPriceLessThan
    @GetMapping("/books/findBooksByPriceLessThan")
    public ResponseEntity<List<BookDto>> findBooksByPriceLessThan(@RequestParam double price){
        Optional<List<Book>> optionalBooks = bookService.findBooksByPriceLessThan(price);
        ResponseEntity<List<BookDto>> bookDtoList = getListResponseEntity(optionalBooks);
        if (bookDtoList != null) return bookDtoList;
        return ResponseEntity.notFound().build();
    }

    //[GET]: findBooksByMiddlePrice
    @GetMapping("/books/findBooksByMiddlePrice")
    public ResponseEntity<List<BookDto>> findBooksByMiddlePrice(@RequestParam double minPrice,
                                                                @RequestParam double maxPrice){
        Optional<List<Book>> optionalBooks = bookService.findBooksByMiddlePrice(minPrice, maxPrice);
        ResponseEntity<List<BookDto>> bookDtoList = getListResponseEntity(optionalBooks);
        if (bookDtoList != null) return bookDtoList;
        return ResponseEntity.notFound().build();
    }

    //[GET]: findBooksByHighPrice
    @GetMapping("/books/findBooksByHighPrice")
    public ResponseEntity<List<BookDto>> findBooksByHighPrice(@RequestParam double minPrice){
        Optional<List<Book>> optionalBooks = bookService.findBooksByHighPrice(minPrice);
        ResponseEntity<List<BookDto>> bookDtoList = getListResponseEntity(optionalBooks);
        if (bookDtoList != null) return bookDtoList;
        return ResponseEntity.notFound().build();
    }

    //[GET]: findBookAndUserByUserId
    @GetMapping("/books/findBookAndUserByUserId/{userId}")
    public ResponseEntity<List<BookDto>> findBookAndUserByUserId(@PathVariable Long userId){
        Optional<List<Book>> optionalBooks = bookService.findBookAndUserByUserId(userId);
        ResponseEntity<List<BookDto>> bookDtoList = getListResponseEntity(optionalBooks);
        if (bookDtoList != null) return bookDtoList;
        return ResponseEntity.notFound().build();
    }



    /**
     * Helper getListResponseEntity;
     * Offer to hide duplicate code;
     * @param optionalBooks as Optional<List<Book>>.
     * @return ResponseEntity<List<BookDto>> as OK. (in other case 'null').
     * */
    private static ResponseEntity<List<BookDto>> getListResponseEntity(Optional<List<Book>> optionalBooks) {
        if(optionalBooks.isPresent()){
            List<Book> bookList = optionalBooks.get();
            List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
            return ok(bookDtoList);
        }
        return null;
    }

}

