package com.app.vp.wookiebooks.controller.mock;

import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.BookService;
import com.app.vp.wookiebooks.service.UserService;
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

import java.util.List;
import java.util.Optional;

import static com.app.vp.wookiebooks.controller.utils.Utils.toJson;
import static com.app.vp.wookiebooks.mapper.UserMapper.mapToListDtoUsers;
import static com.app.vp.wookiebooks.mapper.UserMapper.mapToUserDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserControllerTest {

    private static final String BASE_URL = "/api/user";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

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
                .authorPassword("123123")
                .build();
        //testing endpoint save (userDto that been created) & getting result as response userDto
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .contentType("application/json")
                        .content(toJson(userDto))
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        //find user that has been saved
        Optional<User> optionalUser = Optional.of(userService
                .findUserByAuthorPseudonym(userDto.getAuthorPseudonym())
                .map(u -> {
                    u.setAuthorPassword(userDto.getAuthorPassword());
                    return u;
                })
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
                .authorPassword("123123")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        //getting userId that has been saved
        Long userId = savedUser.getUserId();
        //testing endpoint getUserById & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/{userId}", userId)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //find user that has been saved by userId
        Optional<User> optionalUser = Optional.of(userService
                .getUserById(savedUser.getUserId())
                .orElseThrow());

        //assert that result of endpoint is the same as user been saved
        String actualJson = result.getResponse().getContentAsString();
        String expectedJson = toJson(mapToUserDto(optionalUser.get()));
        assertThat(actualJson).isEqualTo(expectedJson);

    }

    @Test
    void findUserByAuthorPseudonymTest() throws Exception {
        //create new user
        User user = User.builder()
                .authorPseudonym("Link")
                .authorPassword("123123123")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        //getting authorPseudonym that has been saved
        String authorPseudonym = savedUser.getAuthorPseudonym();
        //testing endpoint findUserByAuthorPseudonym & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/authorPseudonym/" + authorPseudonym)
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
        assertEquals(pseudonym, "Link");
    }

    @Test
    void updateAuthorPseudonymTest() throws Exception {
        //create new user
        User user = User.builder()
                .authorPseudonym("Link-Pseudonym1")
                .authorPassword("121231233")
                .build();
        //saving
        User savedUser = userService.createUser(user);
        String authorPseudonym = savedUser.getAuthorPseudonym();
        //creating new author pseudonym that will be paste instead saved
        String newPseudonym = "Link-Pseudonym2";
        //testing endpoint updateAuthorPseudonym & get response
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .put(BASE_URL + "/authorPseudonym/" + authorPseudonym)
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
        assertEquals(pseudonym, "Link-Pseudonym2");
    }

    @Test
    void deleteUserByIdTest() throws Exception {
        //create 2 new users
        User user1 = User.builder()
                .authorPseudonym("Link-1")
                .authorPassword("48899555")
                .build();
        User user2 = User.builder()
                .authorPseudonym("Link-2")
                .authorPassword("48899566")
                .build();
        //saving both
        User savedUser1 = userService.createUser(user1);
        User savedUser2 = userService.createUser(user2);
        //check if they are both been saved
        List<User> allUsers = userService.findAllUsers();
        int allUsersAmountBeforeModification = allUsers.size() + 1; //default Langharra user 'post construct'
        System.out.println("allUsersAmountBeforeModification = " + allUsersAmountBeforeModification);//expected: 3
        //get id from user that wanted to be deleted
        Long userId = savedUser1.getUserId(); //want to delete user1
        //testing endpoint deleteUserById
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(BASE_URL + "/{userId}", userId)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //check if our users amount decremented by 1, (is really user been deleted)
        List<User> allUsersNew = userService.findAllUsers();
        int allUsersAmountAfterModification = allUsersNew.size() + 1; //Longharra as always
        System.out.println("allUsersAmountAfterModification = " + allUsersAmountAfterModification);//expected: 2
        //check if we have expected user after delete user1, so find user2
        Optional<User> optionalUser2 = userService.getUserById(savedUser2.getUserId());
        assertTrue(optionalUser2.isPresent());
        User user2BeenSaved = optionalUser2.get();
        String pseudonym2 = user2BeenSaved.getAuthorPseudonym();
        //assertion by pseudonyms
        assertEquals(pseudonym2, "Link-2");
    }

    @Test
    void findAllUsersTest() throws Exception {
        //create 2 new users
        User user1 = User.builder()
                .authorPseudonym("user1")
                .authorPassword("1233444")
                .build();
        User user2 = User.builder()
                .authorPseudonym("user2")
                .authorPassword("45666888")
                .build();
        //saving both
        userService.createUser(user1);
        userService.createUser(user2);
        //check if they are saved
        List<User> allUsers = userService.findAllUsers();
        int allUsersSize = allUsers.size() + 1;
        System.out.println("allUsersSize = " + allUsersSize); //expected 3
        //testing endpoint findAllUsers
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //check if saved and response are equal
        List<UserDto> usersSaved = mapToListDtoUsers(allUsers);
        System.out.println("usersSaved = " + toJson(usersSaved));//saved
        System.out.println("result = " + result.getResponse().getContentAsString());//response
        assertThat(toJson(usersSaved).equals(result.getResponse().getContentAsString()));

    }

    @Test
    void findUserByBookTitleTest() throws Exception {
        //findUserByBookTitle we path such scenario
        //saving new book with particular and existing author
        //create & save author:
        User user = User.builder() //create
                .authorPseudonym("Author")
                .authorPassword("12356667")
                .build();
        userService.createUser(user); //save
        //create & save book:
        Book book = Book.builder() //create
                .title("TitleExample")
                .author(user)
                .price(20.99)
                .coverImage("cover")
                .description("text")
                .build();
        bookService.createBook(book);//save
        //testing endpoint findUserByBookTitle:
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/title/" + book.getTitle())
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorPseudonym").value(user.getAuthorPseudonym()))
                .andReturn();
        System.out.println("result = " + result.getResponse().getContentAsString());
        //check if saved and response are equal
        assertThat(result.getResponse().getContentAsString().equals(toJson(mapToUserDto(user))));
    }

}