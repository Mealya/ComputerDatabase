package com.excilys.dao.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.DAO;
import com.excilys.dao.ExceptionDAO;
import com.excilys.database.ExceptionDB;
import com.excilys.model.Company;
import com.excilys.utils.EasyConnection;


public class SpringCompanyDAO implements DAO<Company>{

    private final Logger slf4jLogger = LoggerFactory
            .getLogger(SpringCompanyDAO.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Return the List of all the companies.
     * @return The company list
     */
    public List<Company> getAll() {

        //toolConnexion.connect(computer_db_name);
        
        List<Company> result = null;
        Connection connect = null;
        try {
            // Execute a query
            String sql = "SELECT * FROM `company` ";
            connect = dataSource.getConnection();
            if (connect == null) {
                throw new ExceptionDB("No connection ! ");
            }
            PreparedStatement stmt = connect.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            result = new ArrayList<Company>();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column : id | name | introduced | discontinued |
                // company_id
                int id = rs.getInt("id");
                String name = rs.getString("name");

                result.add(new Company(id, name));

            }
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            throw new ExceptionDAO(e.getMessage());
        }  finally {
            EasyConnection.closeConnection(connect);
        }
        return result;
    }

    /**
     * To get a computer with an id.
     * @return The computer 
     */
    @Override
    public Company get(long id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID should not be negative or 0");
        }
        Company compa = null;
        Connection connect = null;
        try {
            //toolConnexion.connect(computer_db_name);

            String sql = "SELECT * FROM `company` WHERE id= ? ;";
            connect = dataSource.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                compa = new Company();
                compa.setId(rs.getInt("id"));
                compa.setName(rs.getString("name"));

            }
            rs.close();
        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            throw new ExceptionDAO(e.getMessage());
        }  finally {
            EasyConnection.closeConnection(connect);
        }
        return compa;
    }
    
    /**
     * Delete the company and all the computers linkeds to the company.
     * @param idCompa The id of the company who needs to be deleted
     */
    public void delete(long id, Connection connect) {
        if (id < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
 
        //Connection connect = null;
        try {
            //String sql1 = "DELETE FROM `computer` WHERE `company_id` = ? ;";
            String sql2 = "DELETE FROM `company` WHERE `id` = ? ;";
            
            connect = dataSource.getConnection();
            connect.setAutoCommit(false);
            
            //PreparedStatement stmt1 = connect.prepareStatement(sql1);
            //stmt1.setLong(1, id);
            PreparedStatement stmt2 = connect.prepareStatement(sql2);
            stmt2.setLong(1, id);
  
            //stmt1.executeUpdate();
            stmt2.executeUpdate();

        } catch (SQLException e) {
            /*slf4jLogger.warn(e.getMessage());
            try {
                connect.rollback();
            } catch (SQLException e1) {
                slf4jLogger.error(e1.getMessage());
            }*/
            throw new ExceptionDAO(e.getMessage());/*
        }  finally {
            toolConnexion.closeConnection(connect);
        */}
    }

}
