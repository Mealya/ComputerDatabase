package com.excilys.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    List<T> getAll() throws SQLException;

    T get(long id) throws SQLException;

    void create(T c) throws SQLException;

    void update(T c) throws SQLException;

    void delete(long c) throws SQLException;
}
