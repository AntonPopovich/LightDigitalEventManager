package ru.lightdigital.testtask.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Login should not be empty")
    @Size(min = 2, max = 40, message = "Login should be between 2 and 40 characters")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters")
    private String password;

    public PersonDTO() {
    }

    public PersonDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
