package com.app.vp.wookiebooks.controller.utils;

import com.app.vp.wookiebooks.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class UserRestClient {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL_TEMPLATE = "http://localhost:8090/api";

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

    public static UserDto findUserById(Long id){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/" + id, UserDto.class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        return userDto;
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

    public static List<UserDto> findAllUsers(){
        ResponseEntity<UserDto[]> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user", UserDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto[] userDto = response.getBody();
        assertNotNull(userDto);
        return Arrays.asList(userDto);
    }

}
