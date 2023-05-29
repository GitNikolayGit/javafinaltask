package ru.cherkas.home.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Route {

    private int id;
    private Country country;
    private Motive motive;
    private Client client;
    private int price;
    @Min(value=0, message = "неверное количество дней")
    private int days;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Motive getMotive() {
        return motive;
    }

    public void setMotive(Motive motive) {
        this.motive = motive;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    public int getDays() { return days; }

    public void setDays(int days) { this.days = days; }

    public int getPrice() { return price; }
    public Route() {}

    public Route(Country country, Motive motive, Client client, int days) {
        this.country = country;
        this.motive = motive;
        this.client = client;
        this.days = days;
        this.price = days * country.getDayPrice() + country.getDayPrice() + country.getVisaPrice();
    }
}
