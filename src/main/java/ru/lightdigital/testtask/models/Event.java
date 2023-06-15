package ru.lightdigital.testtask.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 40 characters")
    private String name;

    @Column(name = "ticket_price")
    @NotEmpty(message = "Ticket price should not be empty")
    @Min(value = 0, message = "Ticket price should be greater than 0")
    private float ticketPrice;

    @JsonBackReference(value = "event-contract")
    @ManyToOne
    @JoinColumn(name = "contract_id",
                referencedColumnName = "id")
    private Contract contract;

    @JsonManagedReference(value = "participant-event")
    @OneToMany(mappedBy = "event")
    private List<Participant> participants;

    public Event() {
    }

    public Event(String name, float ticketPrice, Contract contract) {
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
