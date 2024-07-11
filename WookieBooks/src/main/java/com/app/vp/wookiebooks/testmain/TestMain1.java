package com.app.vp.wookiebooks.testmain;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class TestMain1 {
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL_TEMPLATE = "http://localhost:8083/api";
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //create userDto
        UserDto userDtoCreated = createUser("Login" + System.currentTimeMillis(), "Password1");

        //create token for user
        String token = createToken(userDtoCreated.getAuthorPseudonym(), "Password1");
        System.out.println("token = " + token);

        //create new book with token in headers
        BookDto bookDto = new BookDto(
                "Title1",
                "description1",
                userDtoCreated,
                "defaultPicture1",
                25.5
        );
        BookDto bookDtoCreated = createBook(bookDto, token);
        System.out.println("bookDtoCreated = " + bookDtoCreated);


    }

    private static String createToken(String login, String password ){
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("username", login);
        jsonBody.put("password", password);
        ResponseEntity<String> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/token", jsonBody, String.class);
         return response.getBody();
    }

    private static BookDto createBook(BookDto bookDto, String token) throws JsonProcessingException {
        String json = makeJson(bookDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);

        ResponseEntity<BookDto> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/wookie_books", httpEntity, BookDto.class);
         BookDto bookDtoResponse = response.getBody();
                return bookDtoResponse;


    }

    private static String makeJson(Object dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    private static UserDto createUser(String login, String password){
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("authorPseudonym", login);
        jsonBody.put("authorPassword", password);
        ResponseEntity<UserDto> response =
                restTemplate.postForEntity(BASE_URL_TEMPLATE + "/user", jsonBody, UserDto.class);
         UserDto userDto = response.getBody();
        return userDto;
    }
}

