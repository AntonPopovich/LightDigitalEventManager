package ru.lightdigital.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lightdigital.testtask.dto.ContractDTO;
import ru.lightdigital.testtask.models.Contract;
import ru.lightdigital.testtask.repositories.ContractRepository;
import ru.lightdigital.testtask.util.ContractStatus;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ContractService {
    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Transactional
    public void registerContract(ContractDTO contractDTO) {
        contractRepository.insertContract(ContractStatus.UNDER_CONSIDERATION.toString(),
                contractDTO.getCost(), contractDTO.getPrincipal().getId());
    }

    public Contract findByNumber(int contractNumber) {
        Optional<Contract> contract = contractRepository.findByNumber(contractNumber);
        return contract.orElse(null);
    }
}
