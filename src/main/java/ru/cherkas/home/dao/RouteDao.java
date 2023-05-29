package ru.cherkas.home.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.cherkas.home.controllers.ClientController;
import ru.cherkas.home.controllers.CountryController;
import ru.cherkas.home.controllers.MotiveController;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Country;
import ru.cherkas.home.models.Motive;
import ru.cherkas.home.models.Route;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RouteDao {
    private final JdbcTemplate jdbcTemplate;
    private final CountryDao countryDao;
    private final MotiveDao motiveDao;
    private final ClientDao clientDao;
    String sql = "select route.id, route.days, country.id, motive.id, client.id, client.surname, client.firstName, client.patronymic, client.passport, country.title, country.transportPrice, country.dayPrice, country.visaPrice, motive.title from route join client on route.client_id = client.id join country on route.country_id = country.id join motive on route.motive_id = motive.id";

    @Autowired
    public RouteDao(JdbcTemplate jdbcTemplate, CountryDao countryDao, MotiveDao motiveDao, ClientDao clientDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.countryDao = countryDao;
        this.motiveDao = motiveDao;
        this.clientDao = clientDao;
    }
    public List<Route> index() {
        return jdbcTemplate.query(sql, new RouteMapper());
    }
    // возвращает по паспарту
    //public Optional<Client> show(String passport){
    //    return jdbcTemplate.query("select * from route where passport=?", new Object[] {passport},
    //            new BeanPropertyRowMapper<>(Client.class)).stream().findAny();
   // }
    public Route show(int id) {
        return jdbcTemplate.query(sql +" where id=?", new Object[]{id}, new RouteMapper())
                .stream().findAny().orElse(null);
    }
    // добавить
    public void save(Route route) {

        clientDao.save(route.getClient());

        Optional<Client> client  = clientDao.show(route.getClient().getPassport());
        if (client.isPresent()){
            Client client2 = client.get();
            int id = client2.getId();;

            jdbcTemplate.update("insert into route(country_id, motive_id, client_id, days) values (?, ?, ?, ?)",
                    route.getCountry().getId(), route.getMotive().getId(), id, route.getDays());
        }
    }
    // редактировать
    public void update(int id, Route updatedRoute) {
        jdbcTemplate.update("update route set country=?, motive=?, days=? where id=?",
                updatedRoute.getCountry().getId(), updatedRoute.getMotive().getId(), updatedRoute.getDays(), id);
    }
    // удалить
    public void delete(int id){
        Route route = show(id);
        jdbcTemplate.update("delete from route where id=?", id);
    }
}
