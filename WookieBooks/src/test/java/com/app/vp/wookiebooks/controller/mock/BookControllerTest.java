package com.app.vp.wookiebooks.controller.mock;

import com.app.vp.wookiebooks.controller.utils.Utils;
import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.model.Book;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;
import com.app.vp.wookiebooks.service.BookService;
import com.app.vp.wookiebooks.service.UserService;
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
import java.util.Map;
import java.util.Optional;

import static com.app.vp.wookiebooks.controller.utils.Utils.toJson;
import static com.app.vp.wookiebooks.mapper.BookMapper.mapToBookDto;
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
    void createBook() throws Exception {
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

}