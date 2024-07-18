package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.UserDto;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import static com.app.vp.wookiebooks.controller.utils.UserRestClient.*;
import static com.app.vp.wookiebooks.controller.utils.Utils.verifyStart;
import static org.junit.Assert.*;

@Testcontainers
public class UserControllerContainerTest {


    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose-test.yml"));

    @Test
    public void test() {

        verifyStart(10000L, 5);

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


}
