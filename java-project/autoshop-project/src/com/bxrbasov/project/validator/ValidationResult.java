package com.bxrbasov.project.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    List<Error> errorList = new ArrayList<>();

    public void add(Error error) {
        errorList.add(error);
    }

    public boolean isValid() {
        return errorList.isEmpty();
    }
}
