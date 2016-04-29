package com.excilys.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    default List<T> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    default T get(long id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    default void create(T c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    default void update(T c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    default void delete(long c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
