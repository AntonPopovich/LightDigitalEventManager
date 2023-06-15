package ru.lightdigital.testtask.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fio")
    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 40, message = "FIO should be between 2 and 40 characters")
    private String fio;

    @Column(name = "pcr")
    @NotEmpty(message = "PCR should not be empty")
    @Size(min = 2, max = 40, message = "PCR should be between 2 and 40 characters")
    private String pcr;

    @Column(name = "age")
    @NotEmpty(message = "Age should not be empty")
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 120, message = "Age should be lower than 120")
    private short age;

    @JsonBackReference(value = "person-participant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id",
                referencedColumnName = "id")
    private Person person;

    @JsonBackReference(value = "participant-event")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",
            referencedColumnName = "id")
    private Event event;

    public Participant() {}

    public Participant(int id, String fio, String pcr, short age, Person person, Event event) {
        this.id = id;
        this.fio = fio;
        this.pcr = pcr;
        this.age = age;
        this.person = person;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPcr() {
        return pcr;
    }

    public void setPcr(String pcr) {
        this.pcr = pcr;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", pcr='" + pcr + '\'' +
                ", age=" + age +
                '}';
    }
}
