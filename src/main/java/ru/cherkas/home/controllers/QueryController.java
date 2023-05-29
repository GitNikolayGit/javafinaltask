package ru.cherkas.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.cherkas.home.dao.CountryDao;
import ru.cherkas.home.dao.MotiveDao;
import ru.cherkas.home.dao.QueryDao;
import ru.cherkas.home.dao.RouteDao;
import ru.cherkas.home.models.Country;
import ru.cherkas.home.models.Motive;
import ru.cherkas.home.models.Route;

@Controller
@RequestMapping("/query")
public class QueryController {
    private final RouteDao routeDao;
    private final CountryDao countryDao;
    private final MotiveDao motiveDao;
    private final QueryDao queryDao;

    @Autowired
    public QueryController(RouteDao routeDao, CountryDao countryDao, MotiveDao motiveDao, QueryDao queryDao) {
        this.routeDao = routeDao;
        this.countryDao = countryDao;
        this.motiveDao = motiveDao;
        this.queryDao = queryDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("routes", routeDao.index());
        model.addAttribute("motives", motiveDao.index());
        model.addAttribute("countries", countryDao.index());

        model.addAttribute("motive", new Motive());
        model.addAttribute("route", new Route());
        model.addAttribute("country", new Country());
        return "query/index";
    }
    // Выбирает информацию о маршрутах с заданной целью поездки
    @PostMapping("/query1")
    public String query1(@ModelAttribute("motive") Motive motive, Model model){
        model.addAttribute("routes", queryDao.query1(motive.getId()));

        model.addAttribute("motives", motiveDao.index());
        return "/route/index";
    }
    // Выбирает информацию о маршрутах с заданной целью поездки
    // и стоимостью транспортных услуг менее заданного значения
    @PostMapping("/query2")
    public String query2(@ModelAttribute("route") Route route,  Model model){
        model.addAttribute("routes", queryDao.query2(route.getMotive().getId(), route.getCountry().getTransportPrice()));

        model.addAttribute("motives", motiveDao.index());
        return "/route/index";
    }
    // Выбирает информацию о клиентах, совершивших поездки с
    // заданным количеством дней пребывания в стране
    @PostMapping("/query3")
    public String query3(@ModelAttribute("route") Route route, Model model) {
        model.addAttribute("clients", queryDao.query3(route.getDays()));
        return "/client/index";
    }
}
