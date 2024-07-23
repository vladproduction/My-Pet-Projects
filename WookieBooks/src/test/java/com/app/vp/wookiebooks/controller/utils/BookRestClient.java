package com.app.vp.wookiebooks.controller.utils;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookRestClient {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL_TEMPLATE = "http://localhost:8090/api";

    public static String createToken(String login, String password ){
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("username", login);
        jsonBody.put("password", password);
        ResponseEntity<String> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/token", jsonBody, String.class);
        assertTrue(200 == response.getStatusCode().value());
        return response.getBody();
    }

    public static BookDto createBook(BookDto bookDto, String token) throws JsonProcessingException {
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

    public static String makeJson(Object dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    public static UserDto createUser(String login, String password){
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

    public static BookDto findBookById(Long id){
        ResponseEntity<BookDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/wookie_books/" + id, BookDto.class);
        assertTrue(200 == response.getStatusCode().value());
        BookDto bookDto = response.getBody();
        assertNotNull(bookDto);
        return bookDto;
    }

    //if incorrect input or if not exist:
    public static ResponseEntity<UserDto> findUserByIdOptional(Long id){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/" + id, UserDto.class);
        return response;
    }

    public static UserDto findUserByAuthorPseudonym(String authorPseudonym){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/authorPseudonym/" + authorPseudonym, UserDto.class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        assertEquals(authorPseudonym, userDto.getAuthorPseudonym());
        return userDto;
    }

    //if incorrect input or if not exist:
    public static ResponseEntity<UserDto> findUserByAuthorPseudonymOptional(String authorPseudonym){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/authorPseudonym/" + authorPseudonym, UserDto.class);
        return response;
    }

    public static void updateUserByAuthorPseudonym(String authorPseudonym, String newPseudonym){
        restTemplate.put(BASE_URL_TEMPLATE + "/user/authorPseudonym/" +
                authorPseudonym + "?newPseudonym=" + newPseudonym, new HashMap<>());
    }

    public static void deleteById(Long userId){
        restTemplate.delete(BASE_URL_TEMPLATE + "/user/" + userId);
    }

    public static List<BookDto> findAllBooks(String authorPseudonym,
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
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/wookie_books" + params, BookDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        BookDto[] bookDto = response.getBody();
        assertNotNull(bookDto);
        return Arrays.asList(bookDto);
    }

    public static List<BookDto> findAllBooks(){

        ResponseEntity<BookDto[]> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/wookie_books" , BookDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        BookDto[] bookDto = response.getBody();
        assertNotNull(bookDto);
        return Arrays.asList(bookDto);
    }

}
