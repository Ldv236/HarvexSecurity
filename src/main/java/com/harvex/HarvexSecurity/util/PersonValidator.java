package com.harvex.HarvexSecurity.util;

import com.harvex.HarvexSecurity.models.Person;
import com.harvex.HarvexSecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        //не самое лучшее, временное решение, опирающееся на возникновение исключения при поиске юзера
        try {personDetailsService.loadUserByUsername(person.getUsername()); }
        catch (UsernameNotFoundException ignored) {return;}

        errors.rejectValue("username", "", "such a user already exists");
    }
}
