package ru.cherkas.home.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cherkas.home.dao.CountryDao;
import ru.cherkas.home.dao.MotiveDao;
import ru.cherkas.home.dao.RouteDao;
import ru.cherkas.home.models.Motive;
import ru.cherkas.home.models.Route;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteDao routeDao;
    private final CountryDao countryDao;
    private final MotiveDao motiveDao;

    @Autowired
    public RouteController(RouteDao routeDao, CountryDao countryDao, MotiveDao motiveDao) {
        this.routeDao = routeDao;
        this.countryDao = countryDao;
        this.motiveDao = motiveDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("routes", routeDao.index());
        model.addAttribute("motives", motiveDao.index());

        model.addAttribute("motive", new Motive());
        model.addAttribute("route", new Route());
        return "route/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("routes", routeDao.show(id));
        return "route/show";
    }
    // получить форму
    @GetMapping("/new")
    public String newRoute(@ModelAttribute("route") Route route, Model model) {
        //model.addAttribute("clients", new Client());
        model.addAttribute("countries", countryDao.index());
        model.addAttribute("motives", motiveDao.index());

        return "route/new";
    }
    // добавить
    @PostMapping("/create")
    public String create(@ModelAttribute("route") @Valid Route route, BindingResult bindingResult) {
        // если ошибки возвращаемся
        if (bindingResult.hasErrors())
            return "route/new";

        routeDao.save(route);
        return "redirect:/route";
    }
    // редактировать получить форму
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("routes", routeDao.show(id));
        return "route/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("routes") @Valid Route route,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "route/edit";
        routeDao.update(id, route);
        return "redirect:/route";
    }
    // удалить
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        routeDao.delete(id);
        return "redirect:/route";
    }
}
