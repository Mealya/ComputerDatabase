package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.excilys.database.PoolJdbc;
import com.excilys.database.VirtualJdbc;
import com.excilys.model.Company;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class CompanyDAO implements DAO<Company> {
    
    private final Logger slf4jLogger = LoggerFactory.getLogger(CompanyDAO.class);
   
    private VirtualJdbc toolConnexion = new PoolJdbc();

    public List<Company> getAll() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        //toolConnexion.connect(computer_db_name);
        
        List<Company> result = null;
        Connection connect = null;
        try {
            // Execute a query
            String sql = "SELECT * FROM `company` ";
            connect = toolConnexion.getConnection();
            PreparedStatement stmt = toolConnexion.getConnection().prepareStatement(sql);

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
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
        return result;
    }

    @Override
    public Company get(long id) {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID should not be negative or 0");
        }
        Company compa = null;
        Connection connect = null;
        try {
            //toolConnexion.connect(computer_db_name);

            String sql = "SELECT * FROM `company` WHERE id= ? ;";
            connect = toolConnexion.getConnection();
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
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
        return compa;
    }
    
    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        
        Connection connect = null;
        try {
            String sql_1 = "DELETE FROM `computer` WHERE `company_id` = ? ;";
            String sql_2 = "DELETE FROM `company` WHERE `id` = ? ;";
            
            connect = toolConnexion.getConnection();
            connect.setAutoCommit(false);
            
            PreparedStatement stmt_1 = connect.prepareStatement(sql_1);
            stmt_1.setLong(1, id);
            PreparedStatement stmt_2 = connect.prepareStatement(sql_2);
            stmt_2.setLong(1, id);
  
            stmt_1.executeUpdate();
            stmt_2.executeUpdate();

        } catch (SQLException e) {
            slf4jLogger.warn(e.getMessage());
            try {
                connect.rollback();
            } catch (SQLException e1) {
                slf4jLogger.error(e1.getMessage());
            }
            //throw new ExceptionDAO(e.getMessage());
        }  finally {
            toolConnexion.closeConnection(connect);
        }
    }
}
