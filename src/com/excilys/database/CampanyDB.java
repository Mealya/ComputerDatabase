package com.excilys.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class CampanyDB implements DAO<Company>{

	private Connection connect;

	public CampanyDB(Connection c) throws SQLException {
		if (c == null) {
			throw new IllegalArgumentException("c is null");
		}

		connect = c;
	}
	
	public List<Company> getAll() throws SQLException {
		//Execute a query
		Statement stmt = connect.createStatement();

		String sql = "SELECT * FROM `company` ";
		ResultSet rs = stmt.executeQuery(sql);

		List<Company> result = new ArrayList<>();

		//Extract data from result set
		while (rs.next()) {
			//Retrieve by column : id | name | introduced | discontinued | company_id
			int id = rs.getInt("id");
			String name = rs.getString("name");


			result.add(new Company(id, name));

		}
		rs.close();
		return result;
	}

	@Override
	public Company get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Company c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
