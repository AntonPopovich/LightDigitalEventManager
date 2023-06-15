package ru.lightdigital.testtask.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.models.Principal;
import ru.lightdigital.testtask.repositories.PrincipalRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PrincipalService {
    private PrincipalRepository principalRepository;

    public PrincipalService(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    public List<Principal> findAll() {
        return principalRepository.findAll();
    }

    public Principal findByName (String name) {
        Optional<Principal> foundPrincipal = principalRepository.findByName(name);
        return foundPrincipal.orElse(null);
    }
}
