package ru.lightdigital.testtask.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getContract() {
        ResponseEntity<String> result = template.
                exchange("/new-event/contracts", HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getPrincipal() {
        ResponseEntity<String> result = template.
                exchange("/new-event/principals", HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void createContract() {
        String contractInfo = "{\"principalName\": \"OOO \\\"GAS\\\"\", \"cost\": 450300}";
        HttpEntity<String> request = new HttpEntity<>(contractInfo, getHttpHeaders());
        ResponseEntity<String> result = template.postForEntity("/new-event/contracts/new", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void createEvent() {
        String contractInfo = "{\"name\": \"FUUN2\", \"ticketPrice\": 43.57, \"contractNumber\": 1}";
        HttpEntity<String> request = new HttpEntity<>(contractInfo, getHttpHeaders());
        ResponseEntity<String> result = template.postForEntity("/new-event", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private String getCookieForUser()   {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String person = "{\"login\": \"admin1\", \"password\": \"012312345\"}";

        HttpEntity<String> request = new HttpEntity<>(person, headers);
        ResponseEntity<String> result = template.postForEntity("/auth/signin", request, String.class);

        return result.getHeaders().get("Set-Cookie").get(0);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", getCookieForUser());
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}