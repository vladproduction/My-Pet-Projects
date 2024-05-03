package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.UserMapper;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.repository.UserRepository;
import com.app.vp.wookiebooks.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.dockerjava.api.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static com.app.vp.wookiebooks.mapper.UserMapper.*;
import static com.app.vp.wookiebooks.utils.JsonUtils.toJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer(
            "mysql:8.3.0"
    );

    @DynamicPropertySource
    static void dynamicConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    void createUserTest() throws Exception {
        //create new user
        UserDto userDto = UserDto.builder()
                .authorPseudonym("Neo")
                .build();
        //testing endpoint save (userDto that been created) & getting result as  response userDto
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/wookie_books/user/createUser")
                        .contentType("application/json")
                        .content(toJson(userDto))
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //find user that has been saved
        Optional<User> optionalUser = Optional.of(userService
                .findUserByAuthorPseudonym(userDto.getAuthorPseudonym())
                .orElseThrow());
        //assert that result of endpoint is the same as user been saved
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(toJson(mapToUserDto(optionalUser.get())));

    }

    @Test
    void getUserByIdTest() throws Exception {
        //create new user
        User user = User.builder()
                .authorPseudonym("Trinity")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        //getting userId that has been saved
        Long userId = savedUser.getUserId();
        //testing endpoint getUserById & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/wookie_books/user/getUserById/{userId}", userId)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //find user that has been saved by userId
        Optional<User> optionalUser = Optional.of(userService
                        .getUserById(savedUser.getUserId())
                        .orElseThrow());
        //assert that result of endpoint is the same as user been saved
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(toJson(mapToUserDto(optionalUser.get())));
    }

    @Test
    void findUserByAuthorPseudonymTest() throws Exception {
        //create new user
        User user = User.builder()
                .authorPseudonym("Link")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        //getting authorPseudonym that has been saved
        String authorPseudonym = savedUser.getAuthorPseudonym();
        //testing endpoint findUserByAuthorPseudonym & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/wookie_books/user/findUserByAuthorPseudonym")
                        .param("authorPseudonym", authorPseudonym)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorPseudonym").value(authorPseudonym))
                .andReturn();
        //find user that has been saved by authorPseudonym
        Optional<User> optionalUser = Optional.of(userService
                .findUserByAuthorPseudonym(savedUser.getAuthorPseudonym())
                .orElseThrow());
        //assert that result of endpoint is the same as user been saved
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(toJson(mapToUserDto(optionalUser.get())));

        assertThat(optionalUser.get().getAuthorPseudonym().equals("Link"));
    }

    @Test
    void updateAuthorPseudonymTest() {
    }

    @Test
    void deleteUserByIdTest() {
    }

    @Test
    void findAllUsersTest() {
    }

    @Test
    void findUserByBookTitleTest() {
    }

    @Test
    void deleteBookByUserTest() {
    }

    @Test
    void updateBookByUserTest() {
        //todo: have to define endpoint first
    }
}