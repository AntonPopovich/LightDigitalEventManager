package ru.lightdigital.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.dto.PersonDTO;
import ru.lightdigital.testtask.models.Person;
import ru.lightdigital.testtask.repositories.PersonRepository;

@Service
public class RegistrationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(PersonDTO personDTO) {
        Person person = new Person();
        person.setLogin(personDTO.getLogin());
        person.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        person.setRole("ROLE_USER");

        personRepository.save(person);
    }

}
