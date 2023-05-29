package ru.cherkas.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Country;
import ru.cherkas.home.models.Motive;

import javax.security.auth.login.Configuration;
import java.util.List;
import java.util.Optional;

@Component
public class MotiveDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MotiveDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Motive> index() {
        return jdbcTemplate.query("select * from motive", new BeanPropertyRowMapper<>(Motive.class));// new PersonMapper());
    }
    public Motive show(int id) {
        return jdbcTemplate.query("select * from motive where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Motive.class))
                .stream().findAny().orElse(null);
    }
    // возвращает по названию страны
    public Optional<Motive> show(String title){
        return jdbcTemplate.query("select * from country where title=?", new Object[] {title},
                new BeanPropertyRowMapper<>(Motive.class)).stream().findAny();
    }

    // добавить
    public void save(Motive motive) {
        jdbcTemplate.update("insert into motive(title) values (?)",
                motive.getTitle());
    }
    // редактировать
    public void update(int id, Motive updatedMotive) {
        jdbcTemplate.update("update motive set title=? where id=?",
                updatedMotive.getTitle(), id);

    }
    // удалить
    public void delete(int id){
        jdbcTemplate.update("delete from motive where id=?", id);
    }
}
