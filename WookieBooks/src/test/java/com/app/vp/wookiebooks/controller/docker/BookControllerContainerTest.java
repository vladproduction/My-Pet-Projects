package com.app.vp.wookiebooks.controller.docker;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.dto.UserDto;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.app.vp.wookiebooks.controller.utils.BookRestClient.*;
import static com.app.vp.wookiebooks.controller.utils.Utils.verifyStart;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Testcontainers
public class BookControllerContainerTest {

    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose-test.yml"));

    @Test
    public void test() throws IOException {

        verifyStart(10000L, 5);

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

        List<BookDto> bookDtoListByTitle = findAllBooks(null, bookDto1.getTitle(), null);
        assertTrue(bookDtoListByTitle.size() == 1);
        assertEquals(bookDtoListByTitle.get(0).getTitle(), bookDtoCreated1.getTitle());

        List<BookDto> bookDtoListByPrice = findAllBooks(null, null, bookDto1.getPrice());
        assertTrue(bookDtoListByPrice.size() == 1);
        assertTrue(bookDtoListByPrice.get(0).getPrice() == bookDtoCreated1.getPrice());

        List<BookDto> bookDtoListByPseudo = findAllBooks(userDto.getAuthorPseudonym(), null, null);
        assertTrue(bookDtoListByPseudo.size() == 3);

        List<BookDto> bookDtoListByAllParams = findAllBooks(userDto.getAuthorPseudonym(), bookDto1.getTitle(), bookDto1.getPrice());
        assertTrue(bookDtoListByAllParams.size() == 1);
        assertEquals(bookDtoListByAllParams.get(0).getTitle(), bookDtoCreated1.getTitle());
        assertTrue(bookDtoListByAllParams.get(0).getPrice() == bookDtoCreated1.getPrice());
        assertEquals(bookDtoListByAllParams.get(0).getAuthor().getAuthorPseudonym(), bookDtoCreated1.getAuthor().getAuthorPseudonym());
    }

}
