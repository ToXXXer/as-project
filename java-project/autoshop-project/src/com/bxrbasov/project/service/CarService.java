package com.bxrbasov.project.service;

import com.bxrbasov.project.dao.CarDao;
import com.bxrbasov.project.dto.CarDto;
import com.bxrbasov.project.entity.Car;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private static final CarDao carDao = CarDao.getInstance();
    private static final BrandService brandService = BrandService.getInstance();
    private static final PersonService personService = PersonService.getInstance();
    private static final CarService INSTANCE = new CarService();

    public static CarService getInstance() {
        return INSTANCE;
    }

    public List<CarDto> findAll(Connection connection) {
        return carDao.findAll(connection).stream()
                .map(car -> new CarDto(
                        car.getVin(),
                        brandService.findByPK(car.getBrand().getBrand_name(), connection),
                        car.getModel(),
                        car.getPrice(),
                        car.getMileage(),
                        car.getDate_model(),
                        personService.findByPK(car.getPerson().getSurname(), connection),
                        car.getType_of_engine(),
                        car.getPicture(),
                        car.getCar_info()
                )).collect(Collectors.toList());
    }

    public CarDto findByVin(String vin, Connection connection) {
        Car car = carDao.findByPK(vin, connection);
        return new CarDto(
                car.getVin(),
                brandService.findByPK(car.getBrand().getBrand_name(), connection),
                car.getModel(),
                car.getPrice(),
                car.getMileage(),
                car.getDate_model(),
                personService.findByPK(car.getPerson().getSurname(), connection),
                car.getType_of_engine(),
                car.getPicture(),
                car.getCar_info()
        );
    }

    public List<CarDto> findAllByParameters(Long priceFrom,Long priceTo,Long mileageFrom,Long mileageTo,
                                            Integer yearFrom,Integer yearTo,List<String> typesOfEngine,List<String> brands, Connection connection) {
        return carDao.findAllByParameters(priceFrom, priceTo, mileageFrom, mileageTo, yearFrom, yearTo, typesOfEngine, brands, connection).stream()
                .map(car -> new CarDto(
                        car.getVin(),
                        brandService.findByPK(car.getBrand().getBrand_name(), connection),
                        car.getModel(),
                        car.getPrice(),
                        car.getMileage(),
                        car.getDate_model(),
                        personService.findByPK(car.getPerson().getSurname(), connection),
                        car.getType_of_engine(),
                        car.getPicture(),
                        car.getCar_info()
                )).collect(Collectors.toList());
    }
}
