package ru.lightdigital.testtask.dto;

import ru.lightdigital.testtask.models.Contract;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EventDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 40 characters")
    private String name;

    @NotEmpty(message = "Ticket price should not be empty")
    @Min(value = 0, message = "Ticket price should be greater than 0")
    private float ticketPrice;

    @NotEmpty(message = "Contract Number should not be empty")
    private int contractNumber;

    private Contract contract;

    public EventDTO() {
    }

    public EventDTO(String name, float ticketPrice, Contract contract) {
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
