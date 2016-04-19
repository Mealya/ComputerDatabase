package com.excilys.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class ComputerDB {

	private Connection connect;

	public ComputerDB(Connection c) throws SQLException {
		if (c == null) {
			throw new IllegalArgumentException("c is null");
		}

		connect = c;
	}

	public String printComputers() throws SQLException {
		//Execute a query
		System.out.println("Creating statement...");
		Statement stmt = connect.createStatement();

		String sql = "SELECT * FROM `computer` ";
		ResultSet rs = stmt.executeQuery(sql);
		StringBuilder buff = new StringBuilder();
		buff.append("\n ======= \n Print computers \n ======= \n");
		//Extract data from result set
		while (rs.next()) {
			//Retrieve by column : id | name | introduced | discontinued | company_id
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Timestamp first = rs.getTimestamp("introduced");
			Timestamp last = rs.getTimestamp("discontinued");
			int compID = rs.getInt("company_id");


			buff.append("ID: " + id + ", Nom: " + name 
					+ ", Introduit: " + first + ", Stoppé: " + last +", ID compagnie: " + compID + "\n");
		}
		rs.close();
		return buff.toString();
	}

	public List<Computer> getComputers() throws SQLException {
		//Execute a query
		Statement stmt = connect.createStatement();

		String sql = "SELECT * FROM `computer` ";
		ResultSet rs = stmt.executeQuery(sql);

		List<Computer> result = new ArrayList<>();

		//Extract data from result set
		while (rs.next()) {
			//Retrieve by column : id | name | introduced | discontinued | company_id
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Timestamp first = rs.getTimestamp("introduced");
			Timestamp last = rs.getTimestamp("discontinued");
			int compID = rs.getInt("company_id");

			result.add(new Computer(id, name, first, last, compID));

		}
		rs.close();
		return result;
	}

	public List<Company> getCompagnies() throws SQLException {
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

	public Computer getComputer(String nameCompu) throws SQLException {
		//Execute a query
		Statement stmt = connect.createStatement();

		String sql = "SELECT * FROM `computer` WHERE name=" + nameCompu;
		ResultSet rs = stmt.executeQuery(sql);

		Computer result = null;

		//Extract data from result set
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Timestamp first = rs.getTimestamp("introduced");
			Timestamp last = rs.getTimestamp("discontinued");
			int compID = rs.getInt("company_id");

			result = new Computer(id, name, first, last, compID);

		}
		rs.close();
		return result;
	}
	
	//public void createComputer();
	
	//public void updateComputer();
	
	//public void deleteComputer();
}

