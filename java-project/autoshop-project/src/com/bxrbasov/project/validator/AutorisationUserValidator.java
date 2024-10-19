package com.bxrbasov.project.validator;

import com.bxrbasov.project.dao.PersonDao;
import com.bxrbasov.project.dto.FindUserDto;

import java.sql.Connection;

public class AutorisationUserValidator implements Validation<FindUserDto> {

    private static final PersonDao personDao =PersonDao.getInstance();
    private static final AutorisationUserValidator INSTANCE = new AutorisationUserValidator();

    public static AutorisationUserValidator getInstance() {
        return INSTANCE;
    }


    public ValidationResult isValid(FindUserDto findUserDto) {
        var validationResult = new ValidationResult();

        String surname = findUserDto.getSurname();
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

        String password = findUserDto.getPassword();
        for (Character c: password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                validationResult.add(Error.off("Incorrect character - " + c, "Create new password"));
            }
        }

        return validationResult;
    }

    @Override
    public ValidationResult isValid(FindUserDto findUserDto, Connection connection) {
        var validationResult = new ValidationResult();

        String surname = findUserDto.getSurname();
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

        String password = findUserDto.getPassword();
        for (Character c: password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                validationResult.add(Error.off("Incorrect character - " + c, "Create new password"));
            }
        }

        return validationResult;
    }
}
