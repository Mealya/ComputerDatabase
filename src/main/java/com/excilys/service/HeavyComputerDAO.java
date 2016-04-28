package com.excilys.service;


import java.util.List;

import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Computer;

public class HeavyComputerDAO {

    private ComputerDAO compDB;

    public HeavyComputerDAO() {
        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();
        compDB = new ComputerDAO(tool);
    }

    /*
     * public void setComputerDB(ComputerDB comp) { if (comp == null) { throw
     * new IllegalArgumentException("CompDB is null"); } compDB = comp; }
     */

    public List<Computer> getComputers() {
        List<Computer> result = null;
        result = compDB.getAll();
        return result;
    }

    public Computer getComputer(long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        Computer result = null;
        result = compDB.get(idCompu);
        return result;
    }

    public void createComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        } /*else {
            
             * if (c.getName() == null) { throw new
             * IllegalArgumentException("name of computer is null"); } if
             * (c.getIntro() == null) { throw new
             * IllegalArgumentException("intro of computer is null"); } if
             * (c.getDisco() == null) { throw new
             * IllegalArgumentException("disco of computer is null"); } if
             * (c.getCompId() < 1) { throw new
             * IllegalArgumentException("invalid company id"); }
             *
        }*/
        compDB.create(c);
    }

    public void updateComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }
        compDB.update(c);
    }

    public void deleteComputer(long c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c is negative or 0");
        }
        compDB.delete(c);
    }

    public List<Computer> getSetComputer(int low, int height) {
        /*if (low >= height) {
            throw new IllegalArgumentException("low >= height");
        }
        if (low < 0) {
            throw new IllegalArgumentException("low is negative");
        }
        if (height < 0) {
            throw new IllegalArgumentException("low is negative");
        }*/
        return compDB.getSet(low, height);
    }
    
    public long getSizeTable() {
        return compDB.getSizeTable();
    }
}
