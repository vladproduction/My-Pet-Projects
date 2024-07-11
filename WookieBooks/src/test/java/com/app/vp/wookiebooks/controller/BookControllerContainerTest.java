package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@Testcontainers
public class BookControllerContainerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL_TEMPLATE = "http://localhost:8090/api";


    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose-test.yml"));

    @Test
    public void test() throws URISyntaxException, IOException, InterruptedException {

        //create user
        UserDto userDto = createUser("Login123", "password123");

        //generate token
        String token = createToken(userDto.getAuthorPseudonym(), "password123");

        List<BookDto> allBooksCreated = findAllBooks();
        assertTrue(allBooksCreated.isEmpty());

        //create new book with token in headers
        BookDto bookDto1 = new BookDto(
                "Title1",
                "description1",
                userDto,
                "defaultPicture1",
                25.5
        );
        BookDto bookDtoCreated1 = createBook(bookDto1, token);
        allBooksCreated = findAllBooks();
        assertTrue(allBooksCreated.size() == 1);

        //scenario to create more than one book:
        BookDto bookDto2 = new BookDto(
                "Title2",
                "description2",
                userDto,
                "defaultPicture2",
                26.5
        );
        BookDto bookDtoCreated2 = createBook(bookDto2, token);
        allBooksCreated = findAllBooks();
        assertTrue(allBooksCreated.size() == 2);

        BookDto bookDto3 = new BookDto(
                "Title3",
                "description3",
                userDto,
                "defaultPicture3",
                27.5
        );
        BookDto bookDtoCreated3 = createBook(bookDto3, token);
        allBooksCreated = findAllBooks();
        assertTrue(allBooksCreated.size() == 3);

        //todo: need add 'id' to bookDto
//        findBookById();

        List<BookDto> bookDtoListByTitle = findAllBooks(null, bookDto1.getTitle(), null);
        assertTrue(bookDtoListByTitle.size() == 1);
        assertEquals(bookDtoListByTitle.get(0).getTitle(), bookDtoCreated1.getTitle());

        List<BookDto> bookDtoListByPrice = findAllBooks(null, null, bookDto1.getPrice());
        assertTrue(bookDtoListByPrice.size() == 1);
        assertEquals(bookDtoListByPrice.get(0).getPrice(), bookDtoCreated1.getPrice());

        List<BookDto> bookDtoListByPseudo = findAllBooks(userDto.getAuthorPseudonym(), null, null);
        assertTrue(bookDtoListByPseudo.size() == 3);

        List<BookDto> bookDtoListByAllParams = findAllBooks(userDto.getAuthorPseudonym(), bookDto1.getTitle(), bookDto1.getPrice());
        assertTrue(bookDtoListByAllParams.size() == 1);
        assertEquals(bookDtoListByAllParams.get(0).getTitle(), bookDtoCreated1.getTitle());
        assertEquals(bookDtoListByAllParams.get(0).getPrice(), bookDtoCreated1.getPrice());
        assertEquals(bookDtoListByAllParams.get(0).getAuthor().getAuthorPseudonym(), bookDtoCreated1.getAuthor().getAuthorPseudonym());



    }

    private String createToken(String login, String password ){
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("username", login);
        jsonBody.put("password", password);
        ResponseEntity<String> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/token", jsonBody, String.class);
        assertTrue(200 == response.getStatusCode().value());
        return response.getBody();
    }

    private BookDto createBook(BookDto bookDto, String token) throws JsonProcessingException {
        String json = makeJson(bookDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);

        ResponseEntity<BookDto> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/wookie_books", httpEntity, BookDto.class);
        assertTrue(201 == response.getStatusCode().value());
        BookDto bookDtoResponse = response.getBody();
        assertNotNull(bookDtoResponse);
        assertEquals(bookDto.getTitle(), bookDtoResponse.getTitle());
        return bookDtoResponse;


    }

    private String makeJson(Object dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    private UserDto createUser(String login,  String password){
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("authorPseudonym", login);
        jsonBody.put("authorPassword", password);
        ResponseEntity<UserDto> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/user", jsonBody, UserDto.class);
        assertTrue(201 == response.getStatusCode().value());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        assertEquals(userDto.getAuthorPseudonym(), login);
        return userDto;
    }

    private BookDto findBookById(Long id){
        ResponseEntity<BookDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/wookie_books/" + id, BookDto.class);
        assertTrue(200 == response.getStatusCode().value());
        BookDto bookDto = response.getBody();
        assertNotNull(bookDto);
        return bookDto;
    }

    //if incorrect input or if not exist:
    private ResponseEntity<UserDto> findUserByIdOptional(Long id){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/" + id, UserDto.class);
        return response;
    }

    private UserDto findUserByAuthorPseudonym(String authorPseudonym){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/authorPseudonym/" + authorPseudonym, UserDto.class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        assertEquals(authorPseudonym, userDto.getAuthorPseudonym());
        return userDto;
    }

    //if incorrect input or if not exist:
    private ResponseEntity<UserDto> findUserByAuthorPseudonymOptional(String authorPseudonym){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/authorPseudonym/" + authorPseudonym, UserDto.class);
        return response;
    }

    private void updateUserByAuthorPseudonym(String authorPseudonym, String newPseudonym){
        restTemplate.put(BASE_URL_TEMPLATE + "/user/authorPseudonym/" +
                        authorPseudonym + "?newPseudonym=" + newPseudonym, new HashMap<>());
    }

    private void deleteById(Long userId){
        restTemplate.delete(BASE_URL_TEMPLATE + "/user/" + userId);
    }

    private List<BookDto> findAllBooks(String authorPseudonym,
                                       String title,
                                       Double price){
        String params = "?";
        if(authorPseudonym != null){
            params += "authorPseudonym=" + authorPseudonym + "&";
        }
        if(title != null){
            params += "title=" + title + "&";
        }
        if(price != null){
            params += "price=" + price;
        }
        ResponseEntity<BookDto[]> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + params, BookDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        BookDto[] bookDto = response.getBody();
        assertNotNull(bookDto);
        return Arrays.asList(bookDto);
    }

    private List<BookDto> findAllBooks(){
        return findAllBooks(null, null, null);
    }

    //todo
    //findall
    //update, delete
    //extract methods to other class (user, book)
    //refact
    //by book title need too





}
