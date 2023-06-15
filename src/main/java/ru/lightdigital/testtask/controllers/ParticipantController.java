package ru.lightdigital.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.lightdigital.testtask.dto.ParticipantDTO;
import ru.lightdigital.testtask.models.Event;
import ru.lightdigital.testtask.models.Person;
import ru.lightdigital.testtask.services.EventService;
import ru.lightdigital.testtask.services.ParticipantService;
import ru.lightdigital.testtask.services.PersonService;
import ru.lightdigital.testtask.util.JoiningException;
import ru.lightdigital.testtask.util.PersonErrorResponse;
import ru.lightdigital.testtask.util.PersonNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/events")
public class ParticipantController {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final PersonService personService;

    @Autowired
    public ParticipantController(EventService eventService, ParticipantService participantService, PersonService personService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.personService = personService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinUserToEvent(@RequestBody ParticipantDTO participantDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        participantDTO.setPerson(currentPrincipalName);

        int currentId = personService.findByLogin(currentPrincipalName).getId();

        if(participantService.findByPersonId(currentId) != null) {
            throw new JoiningException("Person with login \"" + currentPrincipalName + "\" already joining to event.");
        }

        participantService.registerParticipant(participantDTO);
        return new ResponseEntity<>("Registration for the event was successful!", HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(JoiningException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
