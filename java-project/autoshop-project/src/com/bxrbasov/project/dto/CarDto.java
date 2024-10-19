package com.bxrbasov.project.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CarDto {
    private String vin;
    private BrandDto brand;
    private String model;
    private Long price;
    private Long mileage;
    private Integer date_model;
    private PersonDto person;
    private String type_of_engine;
    private String picture;
    private String car_info;
}
