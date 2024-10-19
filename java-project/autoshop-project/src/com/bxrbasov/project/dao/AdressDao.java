package com.bxrbasov.project.dao;

import com.bxrbasov.project.entity.Adress;
import com.bxrbasov.project.util.ConnectionManager;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AdressDao implements Dao<Integer, Adress> {

    private static final AdressDao INSTANCE = new AdressDao();

    private AdressDao() {}

    private static final String FIND_BY_ID = """
    SELECT * FROM adress WHERE adress_id = ?;
    """;

    private static final String FIND_BY_COUNTRY_TOWN = """
            SELECT * FROM adress WHERE country = ? AND town = ?;
            """;

    private static final String FIND_ALL = """
            SELECT * FROM adress;
            """;

    private static final String SAVE = """
            INSERT INTO adress(country, town) VALUES (?, ?)
            """;

    @SneakyThrows
    public Adress findByCountryTownOrCreateNew(String country, String town, Connection connection) {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_COUNTRY_TOWN);
        preparedStatement.setString(1, country);
        preparedStatement.setString(2, town);
        ResultSet resultSet = preparedStatement.executeQuery();
        Adress adress = null;
        if(resultSet.next()) {
            adress = buildAdress(resultSet);
        }
        if(adress == null) {
            preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, town);
            preparedStatement.executeUpdate();
            adress = findByCountryTownOrCreateNew(country, town, connection);
        }
        return adress;
    }
    @Override
    public List<Adress> findAll(Connection connection) {
        return List.of();
    }

    @Override
    @SneakyThrows
    public Adress findByPK(Integer id, Connection connection) {
        var preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();
        Adress adress = null;
        if (resultSet.next()) {
            adress = buildAdress(resultSet);
        }
        return adress;
    }

    @Override
    public boolean deleteByPK(Integer id, Connection connection) {
        return false;
    }

    @Override
    public void update(Adress entity, Connection connection) {

    }

    @Override
    public Adress save(Adress entity, Connection connection) {
        return null;
    }

    public static AdressDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Adress buildAdress(ResultSet resultSet) {
        return Adress.builder()
                .adress_id(resultSet.getObject("adress_id", Integer.class))
                .country(resultSet.getObject("country", String.class))
                .town(resultSet.getObject("town", String.class))
                .build();
    }
}
