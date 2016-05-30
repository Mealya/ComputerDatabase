package com.excilys.service;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.utils.OrderType;


public interface ComputerService {
    
    /**
     * Call the DAO to return the computer list.
     * @return The List of computers
     */
    public List<Computer> getComputers();
    
    /**
     * Call the DAO to return a computer linked to the id.
     * @param idCompu The id of the computer
     * @return The computer linked to the id
     */
    public Computer getComputer(Long idCompu);
    
    /**
     * Search a computer by his name and his company name.
     * @param name The name of the Company or the Computer
     * @return Computers founds
     */
    public List<Computer> searchFor(String name);
    
    /**
     * To create a computer.
     * @param c The computer who needs to be added to the DB
     */
    public void createComputer(Computer c);
    
    /**
     * Call the DAO to update a computer.
     * @param c The computer who needs to be update
     */
    public void updateComputer(Computer c);
    
    /**
     * Call the DAO to delete a computer.
     * @param c The computer who needs to be delete
     */
    public void deleteComputer(Long c);
    
    /**
     * Call the DAO to have a set of the computers.
     * @param low First parameter of the LIMIT
     * @param height Second parameter of the LIMIT
     * @return The List who represents the set of computers
     */
    public List<Computer> getSetComputer(int low, int height);
    
    /**
     * @see getSetComputer with a order.
     * @param low First parameter of the LIMIT 
     * @param height Second parameter of the LIMIT
     * @param ord The order of the list
     * @return The List who represents the set of computers
     */
    public List<Computer> getSetComputer(int low, int height, OrderType ord);
    
    /**
     * The total number of computers.
     * @return long represent the number of computers
     */
    public long getSizeTable();
}
