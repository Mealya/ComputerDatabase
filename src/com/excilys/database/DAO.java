package com.excilys.database;

import java.sql.SQLException;


public interface DAO<T> {

	public T get(String name) throws SQLException;
	
	public void create(T c) throws SQLException;
	
	public void update(T c) throws SQLException;
	
	public void delete(T c) throws SQLException;
}
