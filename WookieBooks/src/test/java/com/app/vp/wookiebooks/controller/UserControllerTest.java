package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.mapper.BookMapper;
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

import static com.app.vp.wookiebooks.controller.JsonUtils.toJson;
import static com.app.vp.wookiebooks.mapper.UserMapper.mapToListDtoUsers;
import static com.app.vp.wookiebooks.mapper.UserMapper.mapToUserDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserControllerTest {

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
    void findAllUsersTest() throws Exception {
        //create 2 new users
        User user1 = User.builder()
                .authorPseudonym("user1")
                .build();
        User user2 = User.builder()
                .authorPseudonym("user2")
                .build();
        //saving both
        userService.createUser(user1);
        userService.createUser(user2);
        //check if they are saved
        List<User> allUsers = userService.findAllUsers();
        int allUsersSize = allUsers.size();
        System.out.println("allUsersSize = " + allUsersSize); //expected 2
        //testing endpoint findAllUsers
        var result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/wookie_books/user/findAllUsers")
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
                        .get("/api/wookie_books/user/findUserByBookTitle")
                        .param("title", book.getTitle())
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorPseudonym").value(user.getAuthorPseudonym()))
                .andReturn();
        System.out.println("result = " + result.getResponse().getContentAsString());
        //check if saved and response are equal
        assertThat(result.getResponse().getContentAsString().equals(toJson(mapToUserDto(user))));
    }

    @Test
    void deleteBookByUserTest() throws Exception {
        //scenario:
        //1)create and save user
        User user = User.builder() //create
                .authorPseudonym("John")
                .build();
        User savedUser = userService.createUser(user);//save
        String authorPseudonym = savedUser.getAuthorPseudonym();//get pseudonym to help to find existing user
//2)create a couple of books for this user
        Book book1 = Book.builder() //create
                .title("TestBook1")
                .author(user)
                .price(20.99)
                .coverImage("cover")
                .description("text")
                .build();
        Book book1Saved = bookService.createBook(book1);
        Long bookId = book1Saved.getBookId();
        Book book2 = Book.builder() //create
                .title("TestBook2")
                .author(user)
                .price(20.99)
                .coverImage("cover")
                .description("text")
                .build();
        bookService.createBook(book2);
        //3)find all books by this user
        Optional<List<Book>> books = bookService.findAllBooksByAuthorPseudonym(authorPseudonym);
        //4)define a book wanted to delete
        System.out.println("-------books before delete-------");
        if(books.isPresent()){
            List<Book> bookList = books.get();
            for (Book book : bookList) {
                String bookTitle = book.getTitle();
                System.out.println(bookTitle);
            }
        }
        //5)delete the book
        //testing endpoint
        Long userId = savedUser.getUserId();
        var result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/wookie_books/user/deleteBookByUser/{userId}", userId)
                        .param("bookId", String.valueOf(bookId))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //6)check if book was removed
        Optional<List<Book>> booksUpdated = bookService.findAllBooksByAuthorPseudonym(authorPseudonym);
        System.out.println("-------books after delete-------");
        if(booksUpdated.isPresent()){
            List<Book> bookList = booksUpdated.get();
            for (Book book : bookList) {
                System.out.println(book);
            }
        }
        System.out.println("result = " + result.getResponse().getContentAsString());
        //assertion
        List<Book> bookList = booksUpdated.get();
        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
        assertThat(result.getResponse().getContentAsString().equals(toJson(bookDtoList)));
    }

    @Test
    void updateBookByUserTest() {
        //todo: have to define endpoint first
    }
}