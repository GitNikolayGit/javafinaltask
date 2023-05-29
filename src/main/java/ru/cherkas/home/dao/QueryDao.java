package ru.cherkas.home.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Route;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryDao {
    private final JdbcTemplate jdbcTemplate;
    String sql = "select route.id, route.days, country.id, motive.id, client.id, client.surname, client.firstName, client.patronymic, client.passport, country.title, country.transportPrice, country.dayPrice, country.visaPrice, motive.title from route join client on route.client_id = client.id join country on route.country_id = country.id join motive on route.motive_id = motive.id";


    public QueryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Выбирает информацию о маршрутах с заданной целью поездки
    public List<Route> query1(int id) {
        return jdbcTemplate.query(sql + " where route.motive_id = ?", new Object[]{id}, new RouteMapper());
    }
    // Выбирает информацию о маршрутах с заданной целью поездки и стоимостью
    // транспортных услуг менее заданного значения
    public List<Route> query2(int id, int price){
        return jdbcTemplate.query(sql + " where route.motive_id = ? and country.transportPrice < ?", new Object[]{id, price}, new RouteMapper());
    }
    // Выбирает информацию о клиентах, совершивших поездки с
    // заданным количеством дней пребывания в стране
    public List<Client> query3(int days){
        List<Client> clientList = new ArrayList<>();
        jdbcTemplate.query(sql + " where route.days = ?", new Object[]{days}, new RouteMapper()).forEach(s -> clientList.add(s.getClient()));
        return clientList;
    }
    // Выбирает информацию о маршрутах в заданную страну
    //public List<Route> query4(String country) {
    //    return jdbcTemplate.query(sql + " where route.country.title = ?", new Object[]{country}, new RouteMapper());
    //}
}
