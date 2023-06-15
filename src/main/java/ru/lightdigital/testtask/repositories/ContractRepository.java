package ru.lightdigital.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lightdigital.testtask.models.Contract;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Optional<Contract> findByNumber(int number);

    @Modifying
    @Query(value = "insert into contract (status, cost, principal_id) values (:status, :cost, :principal_id)",
            nativeQuery = true)
    void insertContract(@Param("status") String status, @Param("cost") Float cost,
                        @Param("principal_id") Integer principal_id);
}
