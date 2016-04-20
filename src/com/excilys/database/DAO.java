package com.excilys.database;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {

	public List<T> getAll() throws SQLException;
	
	public T get(int id) throws SQLException;
	
	public void create(T c) throws SQLException;
	
	public void update(T c) throws SQLException;
	
	public void delete(int c) throws SQLException;
}
