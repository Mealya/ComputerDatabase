package com.excilys.database;

import java.sql.SQLException;
import java.util.List;

import com.excilys.model.Computer;

public class HeavyComputerDB {
	
	private ComputerDB compDB;
	
	public HeavyComputerDB(ComputerDB comp) {
		if (comp == null) {
			throw new IllegalArgumentException("CompDB is null");
		}
		compDB = comp;
	}
	public void setComputerDB(ComputerDB comp) {
		if (comp == null) {
			throw new IllegalArgumentException("CompDB is null");
		}
		compDB = comp;
	}
	
	public List<Computer> getComputers() {
		List<Computer> result = null;
		try {
			result = compDB.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Computer getComputer(String nameCompu) {
		Computer result = null;
		try {
			result = compDB.get(nameCompu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void createComputer(Computer c) {
		try {
			compDB.create(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateComputer(Computer c) {
		try {
			compDB.update(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteComputer(Computer c) {
		try {
			compDB.delete(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
