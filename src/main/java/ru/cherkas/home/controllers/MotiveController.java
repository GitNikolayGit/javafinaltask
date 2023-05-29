package ru.cherkas.home.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cherkas.home.dao.MotiveDao;
import ru.cherkas.home.models.Motive;
import ru.cherkas.home.util.MotiveValidator;

@Controller
@RequestMapping("/motive")
public class MotiveController {
    private final MotiveDao motiveDao;
    private final MotiveValidator motiveValidator;

    @Autowired
    public MotiveController(MotiveDao motiveDao, MotiveValidator motiveValidator) {
        this.motiveDao = motiveDao;
        this.motiveValidator = motiveValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("motives", motiveDao.index());
        return "motive/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("motives", motiveDao.show(id));
        return "motive/show";
    }
    // получить форму
    @GetMapping("/new")
    public String newClient(@ModelAttribute("motives") Motive motive) {
        return "motive/new";
    }

    // добавить
    @PostMapping()
    public String create(@ModelAttribute("motives") @Valid Motive motive,
                         BindingResult bindingResult) {
        motiveValidator.validate(motive, bindingResult);
        // если ошибки возвращаемся
        if (bindingResult.hasErrors())
            return "motive/new";
        motiveDao.save(motive);
        return "redirect:/motive";
    }
    // редактировать получить форму
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("motives", motiveDao.show(id));
        return "motive/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("motives") @Valid Motive motive,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "motive/edit";
        motiveDao.update(id, motive);
        return "redirect:/motive";
    }
    // удалить
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        motiveDao.delete(id);
        return "redirect:/motive";
    }

}
