package com.bxrbasov.project.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Car {
    String vin;
    Brand brand;
    String model;
    Long price;
    Long mileage;
    Integer date_model;
    Person person;
    String type_of_engine;
    String picture;
    String car_info;
}
