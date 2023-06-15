package ru.lightdigital.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lightdigital.testtask.models.Principal;

import java.util.Optional;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Integer> {
    Optional<Principal> findByName(String name);
}
