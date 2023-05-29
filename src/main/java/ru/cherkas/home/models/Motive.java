package ru.cherkas.home.models;


import jakarta.validation.constraints.NotEmpty;

public class Motive {

    private int id;
    @NotEmpty(message = "заполните поле")
    private String title;

    public Motive() {}

    public Motive(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
