package com.bxrbasov.project.service;

import com.bxrbasov.project.dao.BrandDao;
import com.bxrbasov.project.dto.BrandDto;
import com.bxrbasov.project.entity.Brand;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class BrandService {

    private static final BrandService INSTANCE = new BrandService();
    private static final BrandDao brandDao = BrandDao.getInstance();

    public static BrandService getInstance() {
        return INSTANCE;
    }

    public BrandDto findByPK(String brand_name, Connection connection) {
        Brand brand = brandDao.findByPK(brand_name, connection);
        return new BrandDto(
                brand.getBrand_name()
        );
    }

    public List<BrandDto> findAll(Connection connection) {
        return brandDao.findAll(connection).stream()
                .map(brand -> new BrandDto(
                        brand.getBrand_name()
                )).collect(Collectors.toList());
    }
}
