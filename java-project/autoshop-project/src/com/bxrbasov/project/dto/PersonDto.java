package com.bxrbasov.project.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PersonDto {
    private String surname;
    private String email;
    private String phone;
    private String first_name;
    private String last_name;
    private String privilege;
    private AdressDto adress;
    private String picture;
    private String user_info;
}
