package ru.lightdigital.testtask.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void authenticateSuccessStatus() {
        String person = "{\"login\": \"test1\", \"password\": \"0123123456\"}";
        HttpEntity<String> request = new HttpEntity<>(person, getHttpHeaders());
        ResponseEntity<String> result = template.postForEntity("/auth/signin", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void createNewPerson() {
        String person = "{\"login\": \"test3\", \"password\": \"12345\"}";
        HttpEntity<String> request = new HttpEntity<>(person, getHttpHeaders());
        ResponseEntity<String> result = template.postForEntity("/auth/new", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}