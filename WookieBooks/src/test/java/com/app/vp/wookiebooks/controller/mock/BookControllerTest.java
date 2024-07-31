package com.app.vp.wookiebooks.controller.mock;

import com.app.vp.wookiebooks.controller.utils.Utils;
import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.BookService;
import com.app.vp.wookiebooks.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.app.vp.wookiebooks.controller.utils.Utils.toJson;
import static com.app.vp.wookiebooks.mapper.BookMapper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class BookControllerTest {

    private static final String BASE_URL = "/api/wookie_books";

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


    private String createToken(String login, String password) throws Exception {
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("username", login);
        loginBody.put("password", password);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(loginBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        return responseJson;
    }

    private BookDto createBookHelper(BookDto bookDto, String token) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(bookDto)))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andReturn();
        String stringAsJson = result.getResponse().getContentAsString();
        BookDto bookDtoResponse = Utils.fromJson(stringAsJson);
        Assertions.assertNotNull(bookDtoResponse);
        Assertions.assertEquals(bookDto, bookDtoResponse);
        return bookDtoResponse;
    }

    @Test
    void createBookTest() throws Exception {
        //1) create user
        User user = User.builder()
                .authorPseudonym("LinkTester")
                .authorPassword("12344555")
                .roles(Roles.USER)
                .build();
        User savedUser = userService.createUser(user);
        //2) generate Token
        String token = createToken(savedUser.getAuthorPseudonym(),  savedUser.getAuthorPassword());
        //3)create book in accordance of auth by token
        Book book = Book.builder()
                .title("Test title unique")
                .description("Test description")
                .author(savedUser)
                .price(20.99)
                .coverImage("Test cover image")
                .build();
        BookDto bookDto = mapToBookDto(book);
        BookDto bookDtoSaved = createBookHelper(bookDto, token);
        //find book that has been saved
        Optional<Book> optionalBook = bookService.findBookByTitle(book.getTitle());
        //assert that result of endpoint is the same as book been saved
        Assertions.assertTrue(optionalBook.isPresent());
        BookDto bookDtoFromService = mapToBookDto(optionalBook.get());
        assertThat(bookDtoSaved).isEqualTo(bookDtoFromService);
        Assertions.assertEquals(bookDtoSaved, bookDtoFromService);
    }

    @Test
    void findBookByIdTest() throws Exception {
        //1)create user
        User user = User.builder()
                .authorPseudonym("Author")
                .authorPassword("1234567")
                .roles(Roles.USER)
                .build();
        User savedUser = userService.createUser(user);
        //2)generate token
        String token = createToken(savedUser.getAuthorPseudonym(), savedUser.getAuthorPassword());
        //3)create book
        Book book = Book.builder()
                .title("Test findBookById")
                .description("Test findBookById description")
                .author(savedUser)
                .price(240.99)
                .coverImage("Test findBookById cover image")
                .build();
        BookDto bookDto = mapToBookDto(book);
        createBookHelper(bookDto, token);
        //4)find book that has been saved (using service)
        Optional<Book> optionalBook = bookService.findBookByTitle(book.getTitle());
        //5)took the id of the book from optionalBook (that has been saved)
        Long bookIdSaved = null;
        if (optionalBook.isPresent()) {
            bookIdSaved = optionalBook.get().getBookId();
        }
        //6)find book byId endpoint (mock MVC)
        var resultMVC = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + "/" + bookIdSaved)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(bookDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //7)assert that books are same
        //---getting saved BookDto as text (json)
        Assertions.assertTrue(optionalBook.isPresent());
        BookDto optionalBookSaved = mapToBookDto(optionalBook.get());
        String jsonSavedBook = toJson(optionalBookSaved);
        //---getting from MVC findByIdBookDto as text (json)
        String jsonResultMVC = resultMVC.getResponse().getContentAsString();
        //---jsonSavedBook is equal to jsonResultMVC:
        Assertions.assertEquals(jsonSavedBook, jsonResultMVC);

        //8)assert that id is same
        Optional<Book> bookSaved_for_Id = Optional.of(bookService.findBookByTitle(book.getTitle())).orElseThrow();
        Assertions.assertTrue(bookSaved_for_Id.isPresent());
        Long actual_Id = bookSaved_for_Id.get().getBookId();
        Assertions.assertEquals(bookIdSaved, actual_Id);
    }

    @Test
    void updateBookTest() throws Exception {
        /*
            //[PUT]: updateBook
            @PutMapping("/{bookId}")
            @ResponseStatus(HttpStatus.OK)
            public ResponseEntity<BookDto> updateBook(
            @RequestBody BookDto bookDto, @PathVariable Long bookId){...}
        */

        //1) create user
        User user = User.builder()
                .authorPseudonym("AuthorA")
                .authorPassword("1234567")
                .roles(Roles.USER)
                .build();
        User savedUser = userService.createUser(user);

        //2) generate token
        String token = createToken(savedUser.getAuthorPseudonym(), savedUser.getAuthorPassword());

        //3) create book
        Book book = Book.builder()
                .title("BookBeforeUpdate")
                .description("Test BookBeforeUpdate description")
                .author(savedUser)
                .price(245.99)
                .coverImage("Test BookBeforeUpdate cover image")
                .build();
        BookDto bookDto = mapToBookDto(book); //mapping to DTO
        BookDto bookHelper = createBookHelper(bookDto, token);//using endpoint for creating book
        Book bookCreated = mapToBook(bookHelper);

        //4) check if book has been saved - optional findBookByTitle (current book using service)
        Optional<Book> optionalBook = Optional.of(bookService.findBookByTitle(bookCreated.getTitle())).orElseThrow();
        Assertions.assertTrue(optionalBook.isPresent());
        //---get ID of current saved book
        Long bookIdSaved = optionalBook.get().getBookId();

        //5) testing endpoint for updating book

        //---create a new BookDto as candidate
        Book bookCandidate = Book.builder()
                .title("BookAfterUpdate")
                .description("Test BookAfterUpdate description")
                .author(savedUser)
                .price(345.99)
                .coverImage("Test BookAfterUpdate cover image")
                .build();
        BookDto bookDtoCandidate = mapToBookDto(bookCandidate); //mapping to DTO

        //---using mock for get result by update endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .put(BASE_URL + "/" + bookIdSaved)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(bookDtoCandidate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //6) assert that saved updated book (expected) is the same as candidate book (actual)

        //---assert that titles are equals:
        //---check if candidateBook has been saved (updated) - optional findBookByTitle (using service)
        Optional<Book> optionalBookUpdated = Optional.of(bookService.findBookByTitle(bookCandidate.getTitle())).orElseThrow();
        Assertions.assertTrue(optionalBookUpdated.isPresent());
        String actualTitle = optionalBookUpdated.get().getTitle();
        String expectedTitle = bookCandidate.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);

        //---assert that authors are same:
        String actualAuthor = optionalBookUpdated.get().getAuthor().getAuthorPseudonym();
        String expectedAuthor = bookCandidate.getAuthor().getAuthorPseudonym();
        Assertions.assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    void findAllBooksTest() throws Exception {
        //1)create users
        //---user1
        //---user2

        //2)create tokens
        //---token1
        //---token2

        //3)create books

        //---books by user1
        //---user1Book1
        //---user1Book2
        //---user1Book3

        //---books by user2
        //---user2Book1
        //---user2Book2
        //---user2Book3

        //4)check if books has been saved (by size)
        List<Book> savedBooks = bookService.findAllBooks();

        //5)testing endpoint findAllBooks
        var resultMvc = mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //6)check if savedBooks and resultMvc are equal (by json)
        List<BookDto> booksSaved = mapToListDtoBooks(savedBooks);
        assertThat(toJson(booksSaved)).isEqualTo(resultMvc.getResponse().getContentAsString());
    }

}