package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.database.JDBCTool;
import com.excilys.model.Company;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class CompanyDAO implements DAO<Company> {

    private String computer_db_name = "computer-database-db";
    private boolean isTesting = false;
    
    private JDBCTool toolConnexion;

    public CompanyDAO(JDBCTool c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }

        toolConnexion = c;
    }

    public void switchDB() {
        if (isTesting) {
            isTesting = true;
            computer_db_name = "computer-database-db-test";
        } else {
            isTesting = false;
            computer_db_name = "computer-database-db";
        }
    }

    public List<Company> getAll() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        toolConnexion.connect(computer_db_name);
        
        List<Company> result = null;
        try {
            // Execute a query
            String sql = "SELECT * FROM `company` ";
            PreparedStatement stmt = toolConnexion.getConnection(
                    computer_db_name).prepareStatement(sql);

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
            rs.close();
            toolConnexion.closeConnect(computer_db_name);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
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
        try {
            toolConnexion.connect(computer_db_name);

            String sql = "SELECT * FROM `company` WHERE id= ? ;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    computer_db_name).prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                compa = new Company();
                compa.setId(rs.getInt("id"));
                compa.setName(rs.getString("name"));

            }
            rs.close();
            toolConnexion.closeConnect(computer_db_name);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return compa;
    }

    @Deprecated
    @Override
    public void create(Company c) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Deprecated
    @Override
    public void update(Company c) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Deprecated
    @Override
    public void delete(long id) throws SQLException {
        // TODO Auto-generated method stub

    }
}
