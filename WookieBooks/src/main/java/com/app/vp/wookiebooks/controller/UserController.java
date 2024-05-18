package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.BookMapper;
import com.app.vp.wookiebooks.mapper.UserMapper;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Spring REST controller handles incoming HTTP requests to RestApiController.
 * It provides endpoints for User (postman):
 * -[POST]      createUser;
 * -[GET]       getUserById;
 * -[GET]       findUserByAuthorPseudonym;
 * -[PUT]       updateAuthorPseudonym;
 * -[DELETE]:   deleteUserById;
 * -[GET]:      findAllUsers
 * -[GET]:      findUserByBookTitle
 * -[DELETE]:   deleteBookByUser
 * Interacts with the following services:
 * -UserService;
 * */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //[POST]: createUser
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto userDto){
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userService.createUser(user);
        UserDto createdUserDto = UserMapper.mapToUserDto(savedUser);
        return ok(createdUserDto);
    }

    //[GET]: getUserById
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserDto>> getUserById(
            @PathVariable Long userId){
        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(Optional.of(userDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findUserByAuthorPseudonym
    @GetMapping("/user/findUserByAuthorPseudonym")
    public ResponseEntity<Optional<UserDto>> findUserByAuthorPseudonym(
            @RequestParam String authorPseudonym){
        Optional<User> userOptional = userService.findUserByAuthorPseudonym(authorPseudonym);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(Optional.of(userDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[PUT]: updateAuthorPseudonym
    @PutMapping("/user/updateAuthorPseudonym")
    public ResponseEntity<Optional<UserDto>> updateAuthorPseudonym(
            @RequestParam String authorPseudonym, @RequestParam String newPseudonym){
        Optional<User> optionalUser = userService.updateAuthorPseudonym(authorPseudonym, newPseudonym);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(Optional.of(userDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[DELETE]: deleteUserById
    @DeleteMapping("/user/deleteUserById/{userId}")
    public ResponseEntity<Optional<UserDto>> deleteUserById(@PathVariable Long userId){
        Optional<User> optionalUser = userService.getUserById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            userService.deleteUserById(userId);
            return ok(Optional.of(userDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findAllUsers
    @GetMapping("/user/findAllUsers")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        List<User> allUsers = userService.findAllUsers();
        List<UserDto> allUsersDto = UserMapper.mapToListDtoUsers(allUsers);
        return ok(allUsersDto);
    }

    //[GET]: findUserByBookTitle
    @GetMapping("/user/findUserByBookTitle")
    public ResponseEntity<Optional<UserDto>> findUserByBookTitle(@RequestParam String title){
        Optional<User> optionalUser = userService.findUserByBookTitle(title);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(Optional.of(userDto));
        }
        return ResponseEntity.notFound().build();
    }

    //[DELETE]: deleteBookByUser
    /**
    * -Implement an endpoint to un-publish a book (DELETE) by steps:
    * 1)have to find user;
    * 2)have to retrieve all books by user was found;
    * 3)find book that needs to remove;
    * 4)remove book;
    * 5)return is: List<Book> updated (show that book been deleted) and convert it as List<BooksDto>;
    * */
    @DeleteMapping("/user/deleteBookByUser/{userId}")
    public ResponseEntity<List<BookDto>> deleteBookByUser(@PathVariable Long userId, @RequestParam Long bookId){
        List<Book> bookList = userService.deleteBookByUser(userId, bookId);
        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
        return ok(bookDtoList);
    }

    //[PATCH]: updateBookByUser
    /**
     * -Implemented an endpoint to update data of the chosen book for current Author by such steps:
     * 1)have to find user;
     * 2)have to retrieve all books by user was found;
     * 3)find book that needs to update;
     * 4)update book and save in repository;
     * 5)return is: List<Book> updated (show that book been updated) and convert it as List<BooksDto>;
     * */
    @PatchMapping("/user/updateBookByUser/{userId}")
    public ResponseEntity<List<BookDto>> updateBookByUser(@PathVariable Long userId,
                                                          @RequestParam Long bookId,
                                                          @RequestBody BookDto candidate){
        Book bookCandidate = BookMapper.mapToBook(candidate);
        List<Book> updatedBooks = userService.updateBookByUser(userId, bookId, bookCandidate);
        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(updatedBooks);
        if(!bookDtoList.isEmpty()){
            return ok(bookDtoList);
        }
        return ResponseEntity.notFound().build();
    }




}
