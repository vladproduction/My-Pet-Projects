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
import java.util.List;
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
        //additional compare
        String pseudonym = optionalUser.get().getAuthorPseudonym();
        assertEquals(pseudonym,"Link");
    }

    @Test
    void updateAuthorPseudonymTest() throws Exception {
        //create new user
        User user = User.builder()
                .authorPseudonym("Link-Pseudonym1")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        String authorPseudonym = savedUser.getAuthorPseudonym();
        //creating new author pseudonym that will be paste instead saved
        String newPseudonym = "Link-Pseudonym2";
        //testing endpoint updateAuthorPseudonym & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/wookie_books/user/updateAuthorPseudonym")
                        .param("authorPseudonym", authorPseudonym)
                        .param("newPseudonym", newPseudonym)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorPseudonym").value(newPseudonym)) //expecting updated
                .andReturn();
        //find user that has been saved with the newPseudonym
        Optional<User> optionalUser = Optional.of(userService
                .findUserByAuthorPseudonym(newPseudonym))
                .orElseThrow();
        //assert that result of endpoint is the same as user been saved
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(toJson(mapToUserDto(optionalUser.get())));
        //additional compare
        String pseudonym = optionalUser.get().getAuthorPseudonym();
        assertEquals(pseudonym,"Link-Pseudonym2");
    }

    @Test
    void deleteUserByIdTest() throws Exception {
        //create 2 new users
        User user1 = User.builder()
                .authorPseudonym("Link-1")
                .build();
        User user2 = User.builder()
                .authorPseudonym("Link-2")
                .build();
        //saving both
        User savedUser1 = userService.createUser(user1);
        User savedUser2 = userService.createUser(user2);
        //check if they are both been saved
        List<User> allUsers = userService.findAllUsers();
        int allUsersAmountBeforeModification = allUsers.size();
        System.out.println("allUsersAmountBeforeModification = " + allUsersAmountBeforeModification);//expected: 2
        //get id from user that wanted to be deleted
        Long userId = savedUser1.getUserId(); //want to delete user1
        //testing endpoint deleteUserById
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/wookie_books/user/deleteUserById/{userId}", userId)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //check if our users amount decremented by 1, (is really user been deleted)
        List<User> allUsersNew = userService.findAllUsers();
        int allUsersAmountAfterModification = allUsersNew.size();
        System.out.println("allUsersAmountAfterModification = " + allUsersAmountAfterModification);//expected: 1
        //check if we have expected user after delete user1, so find user2
        Optional<User> optionalUser2 = userService.getUserById(savedUser2.getUserId());
        User user2BeenSaved = optionalUser2.get();
        String pseudonym2 = user2BeenSaved.getAuthorPseudonym();
        //assertion by pseudonyms
        assertEquals(pseudonym2, "Link-2");
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