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
public class ParticipantControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getEvents() {
        ResponseEntity<String> result = template.
                exchange("/events", HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void joinUserToEvent() {
        String personDataForEvent = "{\"fio\": \"Ivan Petrov\", \"pcr\": \"pcr12\", \"age\": 34, \"person\": \"test1\", \"event\": \"Joyyyy\"}";
        HttpEntity<String> request = new HttpEntity<>(personDataForEvent, getHttpHeaders());
        ResponseEntity<String> result = template.postForEntity("/events/join", request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private String getCookieForUser()   {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String person = "{\"login\": \"test2\", \"password\": \"0123188856\"}";

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