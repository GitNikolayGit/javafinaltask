package ru.cherkas.home.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.cherkas.home.dao.ClientDao;
import ru.cherkas.home.models.Client;

@Component
public class ClientValidator implements Validator {

    private final ClientDao clientDao;

    @Autowired
    public ClientValidator(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
         if (clientDao.show(client.getPassport()).isPresent()){
            errors.rejectValue("passport", "", "Такой паспорт уже есть");
        }
    }
}
