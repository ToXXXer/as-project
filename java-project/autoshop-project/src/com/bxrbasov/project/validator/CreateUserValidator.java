package com.bxrbasov.project.validator;

import com.bxrbasov.project.dao.PersonDao;
import com.bxrbasov.project.dto.CreateUserDto;
import com.bxrbasov.project.entity.Person;
import com.bxrbasov.project.util.ConnectionManager;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class CreateUserValidator implements Validation<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private static final PersonDao personDao = PersonDao.getInstance();

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto createUserDto, Connection connection) {
        var validationResult = new ValidationResult();

        List<Person> personList = personDao.findAll(connection);
        String surname = createUserDto.getSurname();

        if(surname.length() < 5) {
            validationResult.add(Error.off("Too short " + surname.length(), "Create new username with length > 5"));
        }
        if(surname.isEmpty()) {
            validationResult.add(Error.off("Username is empty", "Create new username"));
        }
        for (Character c: surname.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                validationResult.add(Error.off("Incorrect character - " + c, "Create new username"));
            }
        }
        for(Person person: personList) {
            if(person.getSurname().equals(surname)) {
                validationResult.add(Error.off("Username was created before", "Create new username"));
            }
        }

        String email = createUserDto.getEmail();

        for (Character c: email.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || c.equals('@') || c.equals('.') || c.equals('-') || c.equals('_'))) {
                validationResult.add(Error.off("Incorrect character - " + c, "Use other mail"));
            }
        }
        if(email.isEmpty()) {
            validationResult.add(Error.off("Mail's name is empty", "Create new mail"));
        }
        for(Person person: personList) {
            if(person.getEmail().equals(email)) {
                validationResult.add(Error.off("Email already in use", "Create new mail"));
            }
        }

        String password = createUserDto.getPassword();

        for (Character c: password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                validationResult.add(Error.off("Incorrect character - " + c, "Create new password"));
            }
        }

        String repeatPassword = createUserDto.getRepeatPassword();

        if (!password.equals(repeatPassword)) {
            validationResult.add(Error.off("Wrong repeat password" , "Repeat password one more time"));
        }

        return validationResult;
    }

}
