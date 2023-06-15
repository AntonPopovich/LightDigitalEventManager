package ru.lightdigital.testtask.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ParticipantDTO {

    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 40, message = "FIO should be between 2 and 40 characters")
    private String fio;

    @NotEmpty(message = "PCR should not be empty")
    @Size(min = 2, max = 40, message = "PCR should be between 2 and 40 characters")
    private String pcr;

    @NotEmpty(message = "Age should not be empty")
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 120, message = "Age should be lower than 120")
    private short age;

    private String person;

    @NotEmpty(message = "Event should not be empty")
    @Size(min = 2, max = 40, message = "Event should be between 2 and 40 characters")
    private String event;

    public ParticipantDTO() {
    }

    public ParticipantDTO(String fio, String pcr, short age, String person, String event) {
        this.fio = fio;
        this.pcr = pcr;
        this.age = age;
        this.person = person;
        this.event = event;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
