package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.database.PoolJdbc;
import com.excilys.database.VirtualJdbc;
import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.utils.OrderType;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class ComputerDAO implements DAO<Computer> {

    private final Logger slf4jLogger = LoggerFactory.getLogger(ComputerDAO.class);
        
    private VirtualJdbc toolConnexion = new PoolJdbc();
    private static List<Company> cacheCompanies = null;
    

    static {
        CompanyDAO comA = new CompanyDAO();
        cacheCompanies = comA.getAll();
    }

    @Override
    public List<Computer> getAll() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        List<Computer> result = null;
        Connection connect = null;
        try {

            // Execute a query
            String sql = "SELECT * FROM `computer`;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            result = Mapper.resultSetToListComputer(rs, cacheCompanies);

            
            rs.close();
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
        return result;
    }
    
    /**
     * To get a computer linked to the name.
     * @param name The name of the computer
     * @return The computer
     */
    public List<Computer> get(String name) {  
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name should not be null");
        }
        List<Computer> compuTemp = null;
        Connection connect = null;
        try {
    
            // Execute a query
            String sql = "SELECT * FROM `computer` WHERE name = ? ;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            compuTemp = Mapper.resultSetToListComputer(rs, cacheCompanies);

            rs.close();
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
        return compuTemp;
    }
    
    @Override
    public Computer get(long idComp) {
        if (toolConnexion == null) { 
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (idComp <= 0) {
            throw new IllegalArgumentException("ID should not be negative or 0");
        }
        Computer compuTemp = null;
        Connection connect = null;
        try {
         
            // Execute a query
            String sql = "SELECT * FROM `computer` WHERE id = ? ;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            
            stmt.setLong(1, idComp);
            ResultSet rs = stmt.executeQuery();

            compuTemp = Mapper.resultSetToComputer(rs, cacheCompanies);

            rs.close();
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
        return compuTemp;
    }

    @Override
    public void create(Computer comp) {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        
        Connection connect = null;
        try {
            String sql = "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES (?,?,?,?);";

            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);

            stmt.setString(1, comp.getName());
            stmt.setTimestamp(2, comp.getIntro());
            stmt.setTimestamp(3, comp.getDisco());
            if (comp.getComp() == null) {
                stmt.setObject(4, null);
            } else {
                stmt.setObject(4, comp.getComp().getId());
            }


            stmt.executeUpdate();
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
    }

    @Override
    public void update(Computer comp) {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        
        Connection connect = null;
        try {
            String sql = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `computer`.`id` = ?;";

            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            
            stmt.setString(1, comp.getName());
            stmt.setTimestamp(2, comp.getIntro());
            stmt.setTimestamp(3, comp.getDisco());
            if (comp.getComp() == null) {
                stmt.setObject(4, null);
            } else {
                stmt.setObject(4, comp.getComp().getId());
            }
            stmt.setLong(5, comp.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
    }

    @Override
    public void delete(long comp) {
        if (comp < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        
        Connection connect = null;
        try {
            String sql = "DELETE FROM `computer` WHERE `id` = ? ;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setLong(1, comp);
            stmt.executeUpdate();

        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
    }

    /**
     * To have a set of the computers.
     * @param low First parameter of the LIMIT
     * @param height Second parameter of the LIMIT
     * @return The List who represents the set of computers
     */
    public List<Computer> getSet(int low, int height) {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        List<Computer> result = null;
        Connection connect = null;
        try {

            String sql = "SELECT * FROM `computer` LIMIT ?,? ;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, low);
            stmt.setInt(2, height);

            ResultSet rs = stmt.executeQuery();

            result = Mapper.resultSetToListComputer(rs, cacheCompanies);

            rs.close();
            
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        } finally {
            toolConnexion.closeConnection(connect);
        }
        return result;
    }
    
    /**
     * @see getSet with a order.
     * @param low First parameter of the LIMIT 
     * @param height Second parameter of the LIMIT
     * @param ord The order of the list
     * @return The List who represents the set of computers
     */
    public List<Computer> getSet(int low, int height, OrderType ord) {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        List<Computer> result = null;
        Connection connect = null;
        
        String order[] = ord.toString().split(";");
        try {

            String sql = "SELECT * FROM `computer` ORDER BY " + order[0] + " " + order[1] + " LIMIT ?,? ;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, low);
            stmt.setInt(2, height);
            ResultSet rs = stmt.executeQuery();

            result = Mapper.resultSetToListComputer(rs, cacheCompanies);

            rs.close();
            
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        } finally {
            toolConnexion.closeConnection(connect);
        }
        return result;
    }

    /**
     * To get the number of computers.
     * @return Nemeric who represent the number of computers
     */
    public long getSizeTable() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        
        Connection connect = null;
        try {
            String sql = "SELECT COUNT(*) FROM `computer`;";
            
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) {
                return rs.getLong("COUNT(*)");
            }
            
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            //throw new ExceptionDAO(e.getMessage());
        } finally {
            toolConnexion.closeConnection(connect);
        }
        return 0;
    }
}
