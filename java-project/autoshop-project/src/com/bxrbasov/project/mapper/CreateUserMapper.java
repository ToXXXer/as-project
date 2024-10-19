package com.bxrbasov.project.mapper;

import com.bxrbasov.project.dto.CreateUserDto;
import com.bxrbasov.project.entity.Person;

public class CreateUserMapper implements Mapper<CreateUserDto, Person>{

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public Person mapFrom(CreateUserDto object) {
        return Person.builder()
                .surname(object.getSurname())
                .email(object.getEmail())
                .user_password(object.getPassword())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
