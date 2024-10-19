package com.bxrbasov.project.validator;

import lombok.*;

@Getter
@Value(staticConstructor = "off")
public class Error {
    private String code;
    private String message;
}
