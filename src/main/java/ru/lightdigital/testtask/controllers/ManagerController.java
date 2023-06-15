package ru.lightdigital.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lightdigital.testtask.dto.ContractDTO;
import ru.lightdigital.testtask.dto.EventDTO;
import ru.lightdigital.testtask.models.Contract;
import ru.lightdigital.testtask.models.Principal;
import ru.lightdigital.testtask.services.ContractService;
import ru.lightdigital.testtask.services.EventService;
import ru.lightdigital.testtask.services.PrincipalService;
import ru.lightdigital.testtask.util.ContractErrorResponse;
import ru.lightdigital.testtask.util.ContractNotFoundException;
import ru.lightdigital.testtask.util.PrincipalErrorResponse;
import ru.lightdigital.testtask.util.PrincipalNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/new-event")
public class ManagerController {
    private final ContractService contractService;
    private final PrincipalService principalService;
    private final EventService eventService;

    @Autowired
    public ManagerController(ContractService contractService, PrincipalService principalService, EventService eventService) {
        this.contractService = contractService;
        this.principalService = principalService;
        this.eventService = eventService;
    }

    @GetMapping("/contracts")
    public List<Contract> getContract() {
        return contractService.findAll();
    }

    @GetMapping("/principals")
    public List<Principal> getPrincipal() {
        return principalService.findAll();
    }

    @PostMapping("/contracts/new")
    public ResponseEntity<String> createContract(@RequestBody ContractDTO contractDTO){
        String foundPrincipal = contractDTO.getPrincipalName();
        Principal principal = principalService.findByName(foundPrincipal);
        contractDTO.setPrincipal(principal);

        if(principal == null) {
            throw new PrincipalNotFoundException("Principal with name \"" + foundPrincipal + "\" doesn't exist.");
        }

        contractService.registerContract(contractDTO);
        return new ResponseEntity<>("Contract created, wait for approving!", HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PrincipalErrorResponse> handleException(PrincipalNotFoundException e) {
        PrincipalErrorResponse response = new PrincipalErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO){
        int foundContractNumber = eventDTO.getContractNumber();
        Contract contract = contractService.findByNumber(foundContractNumber);
        eventDTO.setContract(contract);

        if(contract == null) {
            throw new ContractNotFoundException("Contract with number \"" + foundContractNumber + "\" doesn't exist. Unable to create event!");
        }

        eventService.createEvent(eventDTO);
        return new ResponseEntity<>("Event created!", HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ContractErrorResponse> handleException(ContractNotFoundException e) {
        ContractErrorResponse response = new ContractErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
