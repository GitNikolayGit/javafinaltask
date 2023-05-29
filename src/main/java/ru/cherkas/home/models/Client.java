package ru.cherkas.home.models;

import jakarta.validation.constraints.NotEmpty;

public class Client {
    private int id;
    @NotEmpty(message = "заполните поле")
    private String surname;
    @NotEmpty(message = "заполните поле")
    private String firstName;
    @NotEmpty(message = "заполните поле")
    private String patronymic;
    @NotEmpty(message = "заполните поле")
    private String passport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
    public Client() {}

    public Client(String surname, String firstName, String patronymic, String passport) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.passport = passport;
    }
}
