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

    private final String COMPUTER_DB_NAME = "computer-database-db";
    private JDBCTool toolConnexion;

    public CompanyDAO(JDBCTool c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }

        toolConnexion = c;
    }

    public List<Company> getAll() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        toolConnexion.connect(COMPUTER_DB_NAME);
        List<Company> result = null;
        try {
            // Execute a query
            String sql = "SELECT * FROM `company` ";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);

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
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
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
        Company compa = null;
        try {
            toolConnexion.connect(COMPUTER_DB_NAME);

            String sql = "SELECT * FROM `company` WHERE id= ? ;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                compa = new Company();
                compa.setId(rs.getInt("id"));
                compa.setName(rs.getString("name"));

            }
            rs.close();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
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
