package com.bxrbasov.project.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll(Connection connection);

    T findByPK(K id, Connection connection);

    boolean deleteByPK(K id, Connection connection);

    void update(T entity, Connection connection);

    T save(T entity, Connection connection);
}
