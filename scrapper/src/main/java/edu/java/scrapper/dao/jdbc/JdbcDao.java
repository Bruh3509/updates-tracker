package edu.java.scrapper.dao.jdbc;

import java.util.List;

public interface JdbcDao<T> {
    void add(T t);

    void remove(long id);

    List<T> findAll();

    List<T> findAll(long id);
}
