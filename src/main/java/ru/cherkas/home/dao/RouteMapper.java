package ru.cherkas.home.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Country;
import ru.cherkas.home.models.Motive;
import ru.cherkas.home.models.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {

    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {

        Country country = new Country(rs.getString("country.title"), rs.getInt("country.transportPrice"),
                rs.getInt("country.dayPrice"), rs.getInt("country.visaPrice"));
        country.setId(rs.getInt("country.id"));

        Motive motive = new Motive(rs.getString("motive.title"));
        motive.setId(rs.getInt("motive.id"));

        Client client = new Client(rs.getString("client.surname"), rs.getString("client.firstName"),
                rs.getString("client.patronymic"), rs.getString("client.passport"));
        client.setId(rs.getInt("client.id"));

        Route route = new Route(country, motive, client, rs.getInt("days"));
        route.setId(rs.getInt("route.id"));

        return route;
    }
}
