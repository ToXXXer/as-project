package com.bxrbasov.project.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateUserDto {
    private String surname;
    private String email;
    private String password;
    private String repeatPassword;
}
