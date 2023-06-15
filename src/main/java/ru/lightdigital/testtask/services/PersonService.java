package ru.lightdigital.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.models.Person;
import ru.lightdigital.testtask.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository pr;

    @Autowired
    public PersonService(PersonRepository pr) {
        this.pr = pr;
    }

    public List<Person> findAll() {
        return pr.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = pr.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        pr.save(person);
    }

    public Person findByLogin(String login) {
        Optional<Person> foundPerson = pr.findByLogin(login);
        return foundPerson.orElse(null);
    }
}
