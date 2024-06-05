package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.UserMapper;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${app.restricted.username}")
    private String RESTRICTED_USER_NAME;


    @Autowired
    private UserService userService;

    //[POST]: createUser
    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        if ((RESTRICTED_USER_NAME).equals(user.getAuthorPseudonym())) {
            user.setRoles(Roles.RESTRICTED_USER);
        }
        User savedUser = userService.createUser(user);
        UserDto createdUserDto = UserMapper.mapToUserDto(savedUser);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //[GET]: getUserById
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findUserByAuthorPseudonym
    @GetMapping("/authorPseudonym/{authorPseudonym}")
    public ResponseEntity<UserDto> findUserByAuthorPseudonym(
            @PathVariable String authorPseudonym) {
        Optional<User> userOptional = userService.findUserByAuthorPseudonym(authorPseudonym);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    //[PUT]: updateAuthorPseudonym
    @PutMapping("/authorPseudonym/{authorPseudonym}")
    public ResponseEntity<UserDto> updateAuthorPseudonym(
            @PathVariable String authorPseudonym, @RequestParam String newPseudonym) {
        Optional<User> optionalUser = userService.updateAuthorPseudonym(authorPseudonym, newPseudonym);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    //[DELETE]: deleteUserById
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            userService.deleteUserById(userId);
            return ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    //[GET]: findAllUsers
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        List<UserDto> list = allUsers.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());

        return ok(list);
    }

    //[GET]: findUserByBookTitle
    @GetMapping("/title/{title}")
    public ResponseEntity<UserDto> findUserByBookTitle(@PathVariable String title) {
        Optional<User> optionalUser = userService.findUserByBookTitle(title);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDto = UserMapper.mapToUserDto(user);
            return ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }


}
