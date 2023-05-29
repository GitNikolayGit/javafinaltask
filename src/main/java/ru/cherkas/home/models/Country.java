package ru.cherkas.home.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Country {
    private int id;
    @NotEmpty(message = "заполните поле")
    private String title;
    @Min(value=0, message = "неверная цена")
    private int transportPrice;
    @Min(value=0, message = "неверная цена")
    private int dayPrice;
    @Min(value=0, message = "неверная цена")
    private int visaPrice;

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

    public int getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(int transportPrice) {
        this.transportPrice = transportPrice;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getVisaPrice() {
        return visaPrice;
    }

    public void setVisaPrice(int visaPrice) {
        this.visaPrice = visaPrice;
    }
    public Country() {}

    public Country(String title, int transportPrice, int dayPrice, int visaPrice) {
        this.title = title;
        this.transportPrice = transportPrice;
        this.dayPrice = dayPrice;
        this.visaPrice = visaPrice;
    }
}
