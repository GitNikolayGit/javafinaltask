package ru.cherkas.home.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.cherkas.home.dao.MotiveDao;
import ru.cherkas.home.models.Client;
import ru.cherkas.home.models.Motive;

@Component
public class MotiveValidator implements Validator {
    private final MotiveDao motiveDao;

    public MotiveValidator(MotiveDao motiveDao) {
        this.motiveDao = motiveDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Motive.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Motive motive = (Motive) target;
          if (motiveDao.show(motive.getTitle()).isPresent()){
              errors.rejectValue("title", "", "Такая цель поездки уже есть");
         }
    }
}
