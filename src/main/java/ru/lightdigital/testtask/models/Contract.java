package ru.lightdigital.testtask.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.lightdigital.testtask.util.ContractStatus;

import java.util.List;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Transient
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 7)
    private ContractStatus status;

    @Column(name = "cost")
    @NotEmpty(message = "Cost should not be empty")
    private float cost;

    @JsonBackReference(value = "principal-contract")
    @ManyToOne
    @JoinColumn(name = "principal_id",
            referencedColumnName = "id")
    private Principal principal;

    @JsonManagedReference(value = "event-contract")
    @OneToMany(mappedBy = "contract")
    private List<Event> events;

    public Contract() {
    }

    public Contract(Principal principal, float cost) {
        this.principal = principal;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
