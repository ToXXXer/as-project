package com.bxrbasov.project.dao;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ImageDao implements Dao<String, Image> {


    @Override
    public List<Image> findAll(Connection connection) {
        return List.of();
    }

    @Override
    public Image findByPK(String id, Connection connection) {
        return null;
    }

    @Override
    public boolean deleteByPK(String id, Connection connection) {
        return false;
    }

    @Override
    public void update(Image entity, Connection connection) {

    }

    @Override
    public Image save(Image entity, Connection connection) {
        return null;
    }

}
