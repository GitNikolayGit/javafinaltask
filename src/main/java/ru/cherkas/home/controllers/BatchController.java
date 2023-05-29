package ru.cherkas.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cherkas.home.dao.ClientDao;

// работа если много значений
@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    private final ClientDao personDao;

    public BatchController(ClientDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping()
    public String index(){
        return "batch/index";
    }

    // пакетная вставка
   // @GetMapping("/without")
   // public String withoutBatch(){
   //     personDao.testMultipleUpdate();
   //     return "redirect:/people";
   // }
   // @GetMapping("/with")
   // public String withBatch(){
   //     personDao.testBatchUpdate();
   //     return "redirect:/people";
   // }
}
