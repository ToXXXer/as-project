package com.bxrbasov.project.service;

import com.bxrbasov.project.dao.PersonDao;
import com.bxrbasov.project.dto.CreateUserDto;
import com.bxrbasov.project.dto.FindUserDto;
import com.bxrbasov.project.dto.PersonDto;
import com.bxrbasov.project.dto.RedactorPersonDto;
import com.bxrbasov.project.entity.Person;
import com.bxrbasov.project.exception.ValidationException;
import com.bxrbasov.project.mapper.CreateUserMapper;
import com.bxrbasov.project.validator.AutorisationUserValidator;
import com.bxrbasov.project.validator.CreateUserValidator;
import com.bxrbasov.project.validator.Error;
import com.bxrbasov.project.validator.RedactorProfileValidator;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private final PersonDao personDao = PersonDao.getInstance();
    private final AdressService adressService = AdressService.getInstance();

    private static final AutorisationUserValidator autorisationUserValidator = AutorisationUserValidator.getInstance();
    private static final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private static final PersonService INSTANCE = new PersonService();

    public static PersonService getInstance() {
        return INSTANCE;
    }

    public PersonDto login(FindUserDto findUserDto, Connection connection) {
        var validationResult = autorisationUserValidator.isValid(findUserDto, connection);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        Person person = personDao.findUser(findUserDto.getSurname(), findUserDto.getPassword(), connection);
        if (person == null) {
            throw new ValidationException(List.of(Error.off("User not found", "Try again") ));
        }
        return new PersonDto(
                person.getSurname(),
                person.getEmail(),
                person.getPhone(),
                person.getFirst_name(),
                person.getLast_name(),
                person.getPrivilege(),
                adressService.findByPK(person.getAdress().getAdress_id(), connection),
                person.getPicture(),
                person.getUser_info()
        );
    }
    public PersonDto findByPK(String surname, Connection connection) {
        Person person = personDao.findByPK(surname, connection);
        return new PersonDto(
                person.getSurname(),
                person.getEmail(),
                person.getPhone(),
                person.getFirst_name(),
                person.getLast_name(),
                person.getPrivilege(),
                adressService.findByPK(person.getAdress().getAdress_id(), connection),
                person.getPicture(),
                person.getUser_info()
        );
    }

    public List<PersonDto> findAll(Connection connection) {
        return personDao.findAll(connection).stream()
                .map(person -> new PersonDto(
                        person.getSurname(),
                        person.getEmail(),
                        person.getPhone(),
                        person.getFirst_name(),
                        person.getLast_name(),
                        person.getPrivilege(),
                        adressService.findByPK(person.getAdress().getAdress_id(), connection),
                        person.getPicture(),
                        person.getUser_info()
                )).collect(Collectors.toList());
    }

    public String create(CreateUserDto createUserDto, Connection connection) {
        var validationResult = createUserValidator.isValid(createUserDto, connection);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        Person person = CreateUserMapper.getInstance().mapFrom(createUserDto);
        personDao.save(person, connection);
        return person.getSurname();
    }

    public PersonDto redactorProfile(RedactorPersonDto build, Connection connection) {
        var validationResult = RedactorProfileValidator.getInstance().isValid(build, connection);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        personDao.redactorProfile(build, connection);
        PersonDto user = PersonService.getInstance().findByPK(build.getSurnameNew(), connection);
        return user;
    }
}
