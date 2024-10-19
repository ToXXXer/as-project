package com.bxrbasov.project.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RedactorPersonDto {
    private String surname;
    private String surnameNew;
    private String email;
    private String phone;
    private String password;
    private String passwordConfirm;
    private String first_name;
    private String last_name;
    private String country;
    private String town;
    private String user_info;
}
