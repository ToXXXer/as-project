package com.bxrbasov.project.service;

import com.bxrbasov.project.dao.AdressDao;
import com.bxrbasov.project.dto.AdressDto;
import com.bxrbasov.project.entity.Adress;

import java.sql.Connection;

public class AdressService {

    private static final AdressService INSTANCE = new AdressService();
    private static final AdressDao adressDao = AdressDao.getInstance();

    public static AdressService getInstance() {
        return INSTANCE;
    }

    public AdressDto findByPK(int id, Connection connection) {
        Adress adress = adressDao.findByPK(id, connection);
        return new AdressDto(
                adress.getCountry(),
                adress.getTown()
        );
    }

}
