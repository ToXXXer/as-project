package com.bxrbasov.project.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
