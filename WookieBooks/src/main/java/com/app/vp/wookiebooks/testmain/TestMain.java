package com.app.vp.wookiebooks.testmain;

import com.app.vp.wookiebooks.dto.BookDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;


public class TestMain {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookDto[]> response =
                restTemplate.getForEntity("http://localhost:8083/api/wookie_books", BookDto[].class);
        System.out.println("response = " + response.getStatusCode());
        System.out.println("response = " + Arrays.toString(response.getBody()));

    }
}

