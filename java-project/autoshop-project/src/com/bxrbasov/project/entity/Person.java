package com.bxrbasov.project.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
    String surname;
    String email;
    String phone;
    String user_password;
    String privilege;
    String first_name;
    String last_name;
    Adress adress;
    String picture;
    String user_info;
}
