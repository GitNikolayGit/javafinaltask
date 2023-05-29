package ru.cherkas.home.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.cherkas.home.dao.CountryDao;
import ru.cherkas.home.models.Country;

@Component
public class CountryValidator implements Validator {
    private final CountryDao countryDao;

    public CountryValidator(CountryDao countryDao) { this.countryDao = countryDao;}

    @Override
    public boolean supports(Class<?> clazz) {
        return Country.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Country country = (Country) target;
         if (countryDao.show(country.getTitle()).isPresent()){
              errors.rejectValue("title", "", "Такая страна уже есть");
         }
    }
}
