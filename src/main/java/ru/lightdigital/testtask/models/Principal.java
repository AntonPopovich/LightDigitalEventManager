package ru.lightdigital.testtask.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "principal")
public class Principal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 40 characters")
    private String name;

    @Column(name = "inn")
    @NotEmpty(message = "Inn should not be empty")
    @Size(min = 10, max = 12, message = "Inn should be between 10 and 12 characters")
    private long inn;

    @JsonManagedReference(value = "principal-contract")
    @OneToMany(mappedBy = "principal")
    private List<Contract> contracts;

    public Principal() {
    }

    public Principal(String name, int inn) {
        this.name = name;
        this.inn = inn;
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

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }
}
