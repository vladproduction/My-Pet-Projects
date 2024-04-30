package com.app.vp.wookiebooks.controller;


import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.UserMapper;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring REST controller handles incoming HTTP requests to RestApiController.
 * It provides endpoints for:
 * -[POST] createUser
 * -//todo
 * Interacts with the following services:
 * -UserService
 * -//todo
 * */
@RestController
@RequestMapping("/api/wookie_books")
public class RestApiController {

    @Autowired
    private UserService userService;

    //Create User REST API
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userService.createUser(user);
        UserDto createdUserDto = UserMapper.mapToUserDto(savedUser);
        return ResponseEntity.ok(createdUserDto);
    }





}
