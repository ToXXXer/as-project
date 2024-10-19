package com.bxrbasov.project.dto;

import lombok.*;

@Value
@Builder
public class FindUserDto {
    private String surname;
    private String password;
}
