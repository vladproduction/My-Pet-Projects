package com.app.vp.wookiebooks.controller;

import com.app.vp.wookiebooks.dto.BookDto;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@Testcontainers
public class BookControllerContainerTest {


    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/compose-test.yml"));

    @Test
    public void test() throws URISyntaxException, IOException, InterruptedException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookDto[]> response =
                restTemplate.getForEntity("http://localhost:8090/api/wookie_books", BookDto[].class);
        assertTrue(200 == response.getStatusCode().value());
        assertTrue(response.getBody().length == 0);

    }




}
