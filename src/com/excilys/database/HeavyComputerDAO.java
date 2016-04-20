package com.excilys.database;

import java.sql.SQLException;
import java.util.List;

import com.excilys.model.Computer;

public class HeavyComputerDAO {

    private ComputerDAO compDB;

    public HeavyComputerDAO(ComputerDAO comp) {
        if (comp == null) {
            throw new IllegalArgumentException("CompDB is null");
        }
        compDB = comp;
    }
    /*
	public void setComputerDB(ComputerDB comp) {
		if (comp == null) {
			throw new IllegalArgumentException("CompDB is null");
		}
		compDB = comp;
	}*/

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

    public Computer getComputer(long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        Computer result = null;
        try {
            result = compDB.get(idCompu);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public void createComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        } else {
            /*if (c.getName() == null) {
                        throw new IllegalArgumentException("name of computer is null");
                    }
                    if (c.getIntro() == null) {
                        throw new IllegalArgumentException("intro of computer is null");
                    }
                    if (c.getDisco() == null) {
                        throw new IllegalArgumentException("disco of computer is null");
                    }
                    if (c.getCompId() < 1) {
                        throw new IllegalArgumentException("invalid company id");
                    }*/
        }
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

    public void deleteComputer(long c) {
        try {
            compDB.delete(c);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Computer> getSetComputer(int low, int height) {
        try {
            return compDB.getSet(low, height);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
