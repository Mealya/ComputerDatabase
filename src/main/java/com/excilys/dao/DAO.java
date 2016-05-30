package com.excilys.dao;

import java.sql.SQLException;
import java.util.List;

@Deprecated
public interface DAO<T> {

    /**
     * To get a list of all T.
     * @return The list of T
     * @throws SQLException Error sql
     */
    default List<T> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * To get a T with an id.
     * @param id The id of T
     * @return T
     * @throws SQLException Error sql
     */
    default T get(long id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * To create a T.
     * @param c The T to create
     * @throws SQLException Error sql
     */
    default void create(T c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * The T to be update.
     * @param c The T to be update
     * @throws SQLException Error sql
     */
    default void update(T c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * The T to be delete.
     * @param c The T to be delete
     * @throws SQLException Error sql
     */
    default void delete(long c) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
