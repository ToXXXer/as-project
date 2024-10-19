package com.bxrbasov.project.dao;

import com.bxrbasov.project.dto.RedactorPersonDto;
import com.bxrbasov.project.entity.Person;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements Dao<String, Person> {

    private static final PersonDao INSTANCE = new PersonDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM person
            """;

    private static final String FIND_BY_ID = """
            SELECT *
            FROM person
            WHERE surname = ?
            """;

    private static final String SAVE = """
            INSERT INTO person(surname, email, user_password) VALUES (?, ?, ?)
            """;

    private static final String FIND_BY_SURNAME_PASSWORD = """
           SELECT *
           FROM person
           WHERE surname = ? AND person.user_password = ?
           """;

    private static final String REDACTOR_PROFILE = """
            UPDATE person 
            SET surname = ?, 
            email = ?, phone = ?, user_password = ?, 
            first_name = ?, last_name = ?, adress_id = ?,
            user_info = ? 
            WHERE surname = ?
            """;

    @SneakyThrows
    public void redactorProfile(RedactorPersonDto build, Connection connection) {
        var preparedStatement = connection.prepareStatement(REDACTOR_PROFILE);
        preparedStatement.setString(1, build.getSurnameNew());
        preparedStatement.setString(2, build.getEmail());
        preparedStatement.setString(3, build.getPhone());
        preparedStatement.setString(4, build.getPassword());
        preparedStatement.setString(5, build.getFirst_name());
        preparedStatement.setString(6, build.getLast_name());
        preparedStatement.setInt(7, AdressDao.getInstance().findByCountryTownOrCreateNew(build.getCountry(), build.getTown(), connection).getAdress_id());
        preparedStatement.setString(8, build.getUser_info());
        preparedStatement.setString(9, build.getSurname());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public Person findUser(String surname, String password, Connection connection) {
        var preparedStatement = connection.prepareStatement(FIND_BY_SURNAME_PASSWORD);
        preparedStatement.setString(1, surname);
        preparedStatement.setString(2, password);
        var resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = buildPerson(resultSet, connection);
        }
        return person;
    }

    @SneakyThrows
    @Override
    public Person findByPK(String surname, Connection connection) {
        var preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setString(1, surname);
        var resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = buildPerson(resultSet, connection);
        }
        return person;
    }

    @Override
    public boolean deleteByPK(String id, Connection connection) {
        return false;
    }

    @Override
    public void update(Person entity, Connection connection) {

    }

    @SneakyThrows
    @Override
    public Person save(Person entity, Connection connection) {
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getSurname());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getUser_password());
        preparedStatement.executeUpdate();
        System.out.println("Perfect!");
        return entity;
    }

    @SneakyThrows
    @Override
    public List<Person> findAll(Connection connection) {
        List<Person> persons = new ArrayList<>();
        var prepared = connection.prepareStatement(FIND_ALL);
        var resultSet = prepared.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            personList.add(buildPerson(resultSet, connection));
        }
        return personList;
    }

    public static PersonDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Person buildPerson(ResultSet resultSet, Connection connection) {
        return Person.builder()
                .surname(resultSet.getObject("surname", String.class))
                .email(resultSet.getObject("email", String.class))
                .phone(resultSet.getObject("phone", String.class))
                .user_password(resultSet.getObject("user_password", String.class))
                .privilege(resultSet.getObject("privilege", String.class))
                .first_name(resultSet.getObject("first_name", String.class))
                .last_name(resultSet.getObject("last_name", String.class))
                .adress(AdressDao.getInstance().findByPK(resultSet.getObject("adress_id", Integer.class), connection))
                .picture(resultSet.getObject("picture", String.class))
                .user_info(resultSet.getObject("user_info", String.class))
                .build();
    }

}
