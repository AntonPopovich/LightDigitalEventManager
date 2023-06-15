package ru.lightdigital.testtask.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.dto.EventDTO;
import ru.lightdigital.testtask.models.Event;
import ru.lightdigital.testtask.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findByName(String name) {
        Optional<Event> foundEvent = eventRepository.findByName(name);
        return foundEvent.orElse(null);
    }

    @Transactional
    public void createEvent(EventDTO eventDTO) {
        ModelMapper modelMapper = new ModelMapper();
        eventRepository.save(modelMapper.map(eventDTO, Event.class));
    }
}
