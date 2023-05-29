package ru.cherkas.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.cherkas.home.models.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> index() {
        return jdbcTemplate.query("select * from client", new BeanPropertyRowMapper<>(Client.class));// new PersonMapper());
    }
    // возвращает по паспарту
    public Optional<Client> show(String passport){
        return jdbcTemplate.query("select * from client where passport=?", new Object[] {passport},
                new BeanPropertyRowMapper<>(Client.class)).stream().findAny();
    }

    public Client show(int id) {
        return jdbcTemplate.query("select * from client where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Client.class))
                .stream().findAny().orElse(null);
    }
    // добавить
    public void save(Client client) {
        jdbcTemplate.update("insert into client(surname, firstName, patronymic, passport) values (?, ?, ?, ?)",
                client.getSurname(), client.getFirstName(), client.getPatronymic(), client.getPassport());
    }
    // редактировать
    public void update(int id, Client updatedPerson) {
        jdbcTemplate.update("update client set surname=?, firstName=?, patronymic=?, passport=? where id=?",
                updatedPerson.getSurname(), updatedPerson.getFirstName(),
                updatedPerson.getPatronymic(), updatedPerson.getPassport(), id);
    }
    // удалить
    public void delete(int id){
        jdbcTemplate.update("delete from client where id=?", id);
    }

    //////////////////////////
    // пакетная вставка
    //////////////////////////
    //private List<Client> create1000People(){
    //    List<Client> personList = new ArrayList<>();
    //    for (int i = 0; i < 1000; i++){
    //        personList.add(new Client("Name" + i, 30, "test" + "mail.ru"));
    //    }
    //    return personList;
    //}
    //// одиночное добавление
    //public void testMultipleUpdate() {
    //    List<Client> personList = create1000People();
    //    long before = System.currentTimeMillis();
    //    for (Client person : personList) {
    //        jdbcTemplate.update("insert into person values (?, ?, ?, ?)", person.getId(), person.getFirstName(), person.getAge(),
    //                person.getEmail());
    //    }
    //    // текущее время
    //    long after = System.currentTimeMillis();
    //    System.out.println("Time: " + (after - before));
    //}
    // пакетное добавление
    //public void testBatchUpdate() {
    //    List<Client> personList = create1000People();
    //    long before = System.currentTimeMillis();
//
    //    jdbcTemplate.batchUpdate("insert into person values(?, ?, ?, ?)",
    //            new BatchPreparedStatementSetter() {
    //                @Override
    //                public void setValues(PreparedStatement ps, int i) throws SQLException {
    //                    ps.setInt(1, personList.get(i).getId());
    //                    ps.setString(2, personList.get(i).getFirstName());
    //                    ps.setInt(3, personList.get(i).getAge());
    //                    ps.setString(4, personList.get(i).getEmail());
    //                }
//
    //                @Override
    //                public int getBatchSize() {
    //                    return personList.size();
    //                }
    //            });
//
    //    long after = System.currentTimeMillis();
    //    System.out.println("Time: " + (after - before));
//
    //}
}