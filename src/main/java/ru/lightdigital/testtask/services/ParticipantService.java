package ru.lightdigital.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.dto.ParticipantDTO;
import ru.lightdigital.testtask.models.Participant;
import ru.lightdigital.testtask.repositories.ParticipantRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final EventService eventService;
    private final PersonService personService;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, EventService eventService, PersonService personService) {
        this.participantRepository = participantRepository;
        this.eventService = eventService;
        this.personService = personService;
    }

    @Transactional
    public void registerParticipant(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setPerson(personService.findByLogin(participantDTO.getPerson()));
        participant.setFio(participantDTO.getFio());
        participant.setPcr(participantDTO.getPcr());
        participant.setAge(participantDTO.getAge());
        participant.setEvent(eventService.findByName(participantDTO.getEvent()));

        participantRepository.save(participant);
    }

    public Participant findByPersonId(int id) {
        Optional<Participant> foundId = participantRepository.findByPersonId(id);
        return foundId.orElse(null);
    }
}
