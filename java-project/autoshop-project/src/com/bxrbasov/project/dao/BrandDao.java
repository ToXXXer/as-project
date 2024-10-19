package com.bxrbasov.project.dao;

import com.bxrbasov.project.entity.Brand;
import com.bxrbasov.project.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrandDao implements Dao<String, Brand> {

    private static BrandDao INSTANCE = new BrandDao();

    private BrandDao() {}

    private static final String FIND_ALL = """
            SELECT DISTINCT(brand_name) FROM brand;
            """;

    private static final String FIND_BY_ID = """
            SELECT brand_name FROM brand WHERE brand_name = ?;
            """;

    public static BrandDao getInstance() {
        return INSTANCE;
    }

    @Override
    @SneakyThrows
    public List<Brand> findAll(Connection connection) {
        List<Brand> list = new ArrayList<>();
        var preparedStatement = connection.prepareStatement(FIND_ALL);
        var resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(buildBrand(resultSet));
        }
        return list;
    }

    @Override
    @SneakyThrows
    public Brand findByPK(String surname, Connection connection) {
        var preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setString(1, surname);
        var resultSet = preparedStatement.executeQuery();
        Brand brand = null;
        if (resultSet.next()) {
            brand = buildBrand(resultSet);
        }
        return brand;
    }

    @Override
    public boolean deleteByPK(String id, Connection connection) {
        return false;
    }

    @Override
    public void update(Brand entity, Connection connection) {

    }

    @Override
    public Brand save(Brand entity, Connection connection) {
        return null;
    }

    @SneakyThrows
    private Brand buildBrand(ResultSet resultSet) {
        return Brand.builder()
                .brand_name(resultSet.getObject("brand_name", String.class))
                .build();
    }
}
