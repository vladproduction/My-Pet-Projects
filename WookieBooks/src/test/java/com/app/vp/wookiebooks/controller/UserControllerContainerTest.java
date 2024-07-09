package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.UserDto;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
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
public class UserControllerContainerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL_TEMPLATE = "http://localhost:8090/api";


    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose-test.yml"));

    @Test
    public void test() throws URISyntaxException, IOException, InterruptedException {

//        Thread.sleep();
        assertTrue(findAllUsers().size() == 1);
        UserDto lohgarra = findUserByAuthorPseudonym("Lohgarra");

        //create
        UserDto userDto1 = createUser("User1", "User1");
        UserDto userDto2 = createUser("User2", "User2");

        //getAll
        List<UserDto> userDtoList = findAllUsers();
        assertNotNull(userDtoList);
        assertTrue(userDtoList.size() == 3);
        assertEquals(userDtoList.get(0), lohgarra);
        assertEquals(userDtoList.get(1), userDto1);
        assertEquals(userDtoList.get(2), userDto2);

        //getById
        UserDto userDtoById1 = findUserById(userDto1.getUserId());
        UserDto userDtoById2 = findUserById(userDto2.getUserId());
        assertEquals(userDtoById1, userDto1);
        assertEquals(userDtoById2, userDto2);

        //getByAuthorPseudonym
        UserDto userDtoAP1 = findUserByAuthorPseudonym(userDto1.getAuthorPseudonym());
        UserDto userDtoAP2 = findUserByAuthorPseudonym(userDto2.getAuthorPseudonym());
        assertEquals(userDtoAP1, userDto1);
        assertEquals(userDtoAP2, userDto2);

        //incorrect byId
//        ResponseEntity<UserDto> userByIdOptional = findUserByIdOptional(-1L);
//        assertTrue(userByIdOptional.getStatusCode().value() == 404);

        //incorrect byAuthorPseudonym
//        ResponseEntity<UserDto> userByAuthorPseudonymOptional = findUserByAuthorPseudonymOptional("-1L");
//        assertTrue(userByAuthorPseudonymOptional.getStatusCode().value() == 404);

        //update
        updateUserByAuthorPseudonym(userDto1.getAuthorPseudonym(), "UserUpdated1");
//        userByAuthorPseudonymOptional = findUserByAuthorPseudonymOptional(userDto1.getAuthorPseudonym());
//        assertTrue(userByAuthorPseudonymOptional.getStatusCode().value() == 404);
        userDtoById1 = findUserById(userDto1.getUserId());
        assertNotNull(userDtoById1);
        assertNotEquals(userDtoById1, userDto1);
        assertNotEquals(userDtoById1.getAuthorPseudonym(), userDto1.getAuthorPseudonym());
        assertEquals(userDtoById1.getUserId(), userDto1.getUserId());

        //deleteById
        deleteById(userDto1.getUserId());
//        ResponseEntity<UserDto> deleteId = findUserByIdOptional(userDto1.getUserId());
//        assertTrue(deleteId.getStatusCode().value() == 404);

        //finAllAfterDelete
        assertTrue(findAllUsers().size() == 2);


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

    private UserDto findUserById(Long id){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user/" + id, UserDto.class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto userDto = response.getBody();
        assertNotNull(userDto);
        return userDto;
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

    private List<UserDto> findAllUsers(){
        ResponseEntity<UserDto[]> response =
                restTemplate.getForEntity(BASE_URL_TEMPLATE + "/user", UserDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        UserDto[] userDto = response.getBody();
        assertNotNull(userDto);
        return Arrays.asList(userDto);
    }

    //by book title need too





}
