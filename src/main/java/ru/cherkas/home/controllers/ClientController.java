package ru.cherkas.home.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cherkas.home.dao.ClientDao;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.util.ClientValidator;

@Controller
@RequestMapping()
public class ClientController {

    private final ClientDao clientDao;
    private final ClientValidator clientValidator;
    @Autowired
    public ClientController(ClientDao clientDao, ClientValidator clientValidator){
        this.clientDao = clientDao;
        this.clientValidator = clientValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("clients", clientDao.index());
        return "../../index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("clients", clientDao.show(id));
        return "client/show";
    }
    // получить форму
    @GetMapping("/new")
    public String newClient(@ModelAttribute("clients") Client person) {
        return "client/new";
    }
    // добавить
    @PostMapping("/")
    public String create(@ModelAttribute("clients") @Valid Client client,
                         BindingResult bindingResult) {
        clientValidator.validate(client, bindingResult);
        // если ошибки возвращаемся
        if (bindingResult.hasErrors())
            return "client/new";
        clientDao.save(client);
        return "redirect:/";
    }
    // редактировать получить форму
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("clients", clientDao.show(id));
        return "client/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("clients") @Valid Client client,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "client/edit";
        clientDao.update(id, client);
        return "redirect:/";
    }
    // удалить
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clientDao.delete(id);
        return "redirect:/";
    }
}
