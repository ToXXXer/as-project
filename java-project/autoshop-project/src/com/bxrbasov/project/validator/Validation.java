package com.bxrbasov.project.validator;

import java.sql.Connection;

interface Validation<T> {

    ValidationResult isValid(T t, Connection connection);
}
