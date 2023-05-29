package ru.cherkas.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Country;
import ru.cherkas.home.models.Motive;

import java.util.List;
import java.util.Optional;

@Component
public class CountryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> index() {
        return jdbcTemplate.query("select * from country", new BeanPropertyRowMapper<>(Country.class));// new PersonMapper());
    }

    // возвращает по названию страны
    public Optional<Country> show(String title){
        return jdbcTemplate.query("select * from country where title=?", new Object[] {title},
                new BeanPropertyRowMapper<>(Country.class)).stream().findAny();
    }

    public Country show(int id) {
        return jdbcTemplate.query("select * from country where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Country.class))
                .stream().findAny().orElse(null);
    }
    // добавить
    public void save(Country country) {
        jdbcTemplate.update("insert into country(title, transportPrice, dayPrice, visaPrice) values (?, ?, ?, ?)",
                country.getTitle(), country.getTransportPrice(), country.getDayPrice(), country.getVisaPrice());
    }
    // редактировать
    public void update(int id, Country updatedCountry) {
        jdbcTemplate.update("update country set title=?, transportPrice=?, dayPrice=?, visaPrice=? where id=?",
                updatedCountry.getTitle(), updatedCountry.getTransportPrice(), updatedCountry.getDayPrice(),
                updatedCountry.getVisaPrice(), id);
    }
}
