package com.excilys.service;


import java.util.List;

import com.excilys.dao.ComputerDAO;
import com.excilys.dao.spring.SpringComputerDAO;
import com.excilys.database.SpringDataSource;
import com.excilys.model.Computer;
import com.excilys.utils.OrderType;

public class HeavyComputerDAO {

    private SpringComputerDAO compDB;

    /**
     * Create a service linked to the ComputerDAO.
     */
    public HeavyComputerDAO() {
        compDB = (SpringComputerDAO) SpringDataSource.getContext().getBean("SpringComputerDAO");
    }

    /**
     * Call the DAO to return the computer list.
     * @return The List of computers
     */
    public List<Computer> getComputers() {
        List<Computer> result = null;
        result = compDB.getAll();
        return result;
    }

    /**
     * Call the DAO to return a computer linked to the id.
     * @param idCompu The id of the computer
     * @return The computer linked to the id
     */
    public Computer getComputer(long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        Computer result = null;
        result = compDB.get(idCompu);
        return result;
    }
    
    public List<Computer> searchFor(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name non valide");
        }
        List<Computer> result = null;
        result = compDB.searchFor(name);
        return result;
    }
    /**
     * Call the DAO to create a computer.
     * @param c The computer who needs to be added to the DB
     */
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
    
    /**
     * Call the DAO to update a computer.
     * @param c The computer who needs to be update
     */
    public void updateComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }
        compDB.update(c);
    }

    /**
     * Call the DAO to delete a computer.
     * @param c The computer who needs to be delete
     */
    public void deleteComputer(long c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c is negative or 0");
        }
        compDB.delete(c);
    }

    /**
     * Call the DAO to have a set of the computers.
     * @param low First parameter of the LIMIT
     * @param height Second parameter of the LIMIT
     * @return The List who represents the set of computers
     */
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
    
    /**
     * @see getSetComputer with a order.
     * @param low First parameter of the LIMIT 
     * @param height Second parameter of the LIMIT
     * @param ord The order of the list
     * @return The List who represents the set of computers
     */
    public List<Computer> getSetComputer(int low, int height, OrderType ord) {
        /*if (low >= height) {
            throw new IllegalArgumentException("low >= height");
        }
        if (low < 0) {
            throw new IllegalArgumentException("low is negative");
        }
        if (height < 0) {
            throw new IllegalArgumentException("low is negative");
        }*/
        return compDB.getSet(low, height, ord);
    }
    
    /**
     * The total number of computers.
     * @return long represent the number of computers
     */
    public long getSizeTable() {
        return compDB.getSizeTable();
    }
}
