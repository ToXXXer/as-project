package com.bxrbasov.project.dao;

import com.bxrbasov.project.entity.Car;
import com.bxrbasov.project.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarDao implements Dao<String, Car> {

    private static final CarDao INSTANCE = new CarDao();



    private static final String FIND_ALL = """
            SELECT *
            FROM car
            """;

    private static final String FIND_BY_ID = """
            SELECT *
            FROM car
            WHERE vin = ?
            """;

    private static final String FIND_ALL_BY_PARAMETERS = """
            SELECT * FROM car
            WHERE price BETWEEN ? AND ? AND
            mileage BETWEEN ? AND ? AND
            date_model BETWEEN ? AND ? AND
            type_of_engine = ? AND
            brand = ?;
            """;

    @SneakyThrows
    @Override
    public List<Car> findAll(Connection connection) {
        List<Car> cars = new ArrayList<>();
        var prepared = connection.prepareStatement(FIND_ALL);
        var resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            cars.add(buildCar(resultSet, connection));
        }
        return cars;
    }

    @SneakyThrows
    @Override
    public Car findByPK(String vin, Connection connection) {
        var preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setString(1, vin);
        var resultSet = preparedStatement.executeQuery();
        Car car = null;
        if (resultSet.next()) {
            car = buildCar(resultSet, connection);
        }
        return car;
    }

    @Override
    public boolean deleteByPK(String id, Connection connection) {
        return false;
    }

    @Override
    public void update(Car entity, Connection connection) {

    }

    @Override
    public Car save(Car entity, Connection connection) {
        return null;
    }

    @SneakyThrows
    public List<Car> findAllByParameters(Long priceFrom, Long priceTo, Long mileageFrom, Long mileageTo,
                                         Integer yearFrom, Integer yearTo, List<String> typesOfEngine, List<String> brands, Connection connection) {
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < brands.size(); i++) {
            String brand = brands.get(i);
            for(int j = 0; j < typesOfEngine.size(); j++) {
                String type_of_engine = typesOfEngine.get(j);
                var prepared = connection.prepareStatement(FIND_ALL_BY_PARAMETERS);
                prepared.setLong(1, priceFrom);
                prepared.setLong(2, priceTo);
                prepared.setLong(3, mileageFrom);
                prepared.setLong(4, mileageTo);
                prepared.setInt(5, yearFrom);
                prepared.setInt(6, yearTo);
                prepared.setString(7, type_of_engine);
                prepared.setString(8, brand);
                var resultSet = prepared.executeQuery();
                while (resultSet.next()) {
                    cars.add(buildCar(resultSet, connection));
                }
            }
        }
        return cars;
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Car buildCar(ResultSet resultSet, Connection connection) {
        return Car.builder()
                .vin(resultSet.getObject("vin", String.class))
                .brand(BrandDao.getInstance().findByPK(resultSet.getObject("brand", String.class), connection))
                .model(resultSet.getObject("model", String.class))
                .price(resultSet.getObject("price", Long.class))
                .mileage(resultSet.getObject("mileage", Long.class))
                .date_model(resultSet.getObject("date_model", Integer.class))
                .person(PersonDao.getInstance().findByPK(resultSet.getObject("surname", String.class), connection))
                .type_of_engine(resultSet.getObject("type_of_engine", String.class))
                .picture(resultSet.getObject("picture", String.class))
                .car_info(resultSet.getObject("car_info", String.class))
                .build();
    }

}
