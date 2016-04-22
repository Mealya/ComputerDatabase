package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.database.JDBCTool;
import com.excilys.model.Company;
import com.excilys.model.Computer;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class ComputerDAO implements DAO<Computer> {

    private final String COMPUTER_DB_NAME = "computer-database-db";
    private JDBCTool connection;
    private static List<Company> cacheCompanies = null;

    public void setConnexion(JDBCTool c) {
        if (c == null) {
            throw new IllegalArgumentException("Tool is null");
        }
        connection = c;
        // connection.linkToMySql();
    }

    public JDBCTool getConnexion() {
        return connection;
    }

    private List<Company> initCompanies() throws SQLException {
        if (cacheCompanies == null) {
            CompanyDAO comA = new CompanyDAO(connection);
            cacheCompanies = comA.getAll();
        }
        return cacheCompanies;
    }

    @Override
    public List<Computer> getAll() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        initCompanies();
        connection.connect(COMPUTER_DB_NAME);
        // Execute a query
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        // SELECT c.id, c.name, c.introduced, c.discontinued, com.name FROM
        // computer c INNER JOIN company com ON com.id = company_id
        String sql = "SELECT * FROM `computer` ;";
        ResultSet rs = stmt.executeQuery(sql);

        List<Computer> result = new ArrayList<>();

        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column : id | name | introduced | discontinued |
            // company_id
            Computer compuTemp = new Computer();
            compuTemp.setId(rs.getLong("id"));
            compuTemp.setName(rs.getString("name"));
            compuTemp.setIntro(rs.getTimestamp("introduced"));
            compuTemp.setDisco(rs.getTimestamp("discontinued"));

            // CompanyDAO compDAO = new CompanyDAO(connection);
            // Company c = compDAO.get(rs.getInt("company_id"));
            for (Company c : cacheCompanies) {
                if (c.getId() == rs.getLong("company_id")) {
                    compuTemp.setComp(c);
                }
            }
            result.add(compuTemp);

        }
        rs.close();
        connection.closeConnect(COMPUTER_DB_NAME);
        return result;
    }

    @Override
    public Computer get(long idComp) throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        initCompanies();
        connection.connect(COMPUTER_DB_NAME);
        // Execute a query
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        String sql = "SELECT * FROM `computer` WHERE id=" + idComp + ";";
        ResultSet rs = stmt.executeQuery(sql);

        Computer compuTemp = null;

        // Extract data from result set
        while (rs.next()) {
            compuTemp = new Computer();
            compuTemp.setId(rs.getLong("id"));
            compuTemp.setName(rs.getString("name"));
            compuTemp.setIntro(rs.getTimestamp("introduced"));
            compuTemp.setDisco(rs.getTimestamp("discontinued"));

            // CompanyDAO compDAO = new CompanyDAO(connection);
            // Company c = compDAO.get(rs.getInt("company_id"));
            for (Company c : cacheCompanies) {
                if (c.getId() == rs.getLong("company_id")) {
                    compuTemp.setComp(c);
                }
            }

        }

        rs.close();
        connection.closeConnect(COMPUTER_DB_NAME);
        return compuTemp;
    }

    @Override
    public void create(Computer comp) throws SQLException {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        connection.connect(COMPUTER_DB_NAME);
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        String sql = "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES ('"
                + comp.getName()
                + "', '"
                + comp.getIntro()
                + "', '"
                + comp.getDisco() + "'," + comp.getComp().getId() + ");";

        sql = sql.replaceAll("'null'", "NULL");
        if (comp.getId() == 0) {
            sql = sql.replaceAll("0\\);", "NULL\\);");
        }
        stmt.executeUpdate(sql);
        connection.closeConnect(COMPUTER_DB_NAME);
    }

    @Override
    public void update(Computer comp) throws SQLException {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        connection.connect(COMPUTER_DB_NAME);
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        String sql = "UPDATE `computer` SET `name` = '" + comp.getName()
                + "', `introduced` = '" + comp.getIntro()
                + "', `discontinued` = '" + comp.getDisco()
                + "', `company_id` = '" + comp.getComp().getId()
                + "' WHERE `computer`.`id` = " + comp.getId() + ";";
        sql = sql.replaceAll("'null'", "NULL");
        if (comp.getId() == 0) {
            sql = sql.replaceAll("0\\);", "NULL\\);");
        }
        stmt.executeUpdate(sql);
        connection.closeConnect(COMPUTER_DB_NAME);
    }

    @Override
    public void delete(long comp) throws SQLException {
        if (comp < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        connection.connect(COMPUTER_DB_NAME);
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        String sql = "DELETE FROM `computer` WHERE `id` = " + comp + ";";
        stmt.executeUpdate(sql);
        connection.closeConnect(COMPUTER_DB_NAME);
    }

    public List<Computer> getSet(int low, int height) throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        initCompanies();
        connection.connect(COMPUTER_DB_NAME);
        // Execute a query
        Statement stmt = connection.getConnection(COMPUTER_DB_NAME)
                .createStatement();

        String sql = "SELECT * FROM `computer` LIMIT " + low + "," + height
                + ";";
        ResultSet rs = stmt.executeQuery(sql);

        List<Computer> result = new ArrayList<>();

        // Extract data from result set
        while (rs.next()) {
            // Retrieve by column : id | name | introduced | discontinued |
            // company_id
            Computer compuTemp = new Computer();
            compuTemp.setId(rs.getLong("id"));
            compuTemp.setName(rs.getString("name"));
            compuTemp.setIntro(rs.getTimestamp("introduced"));
            compuTemp.setDisco(rs.getTimestamp("discontinued"));

            // CompanyDAO compDAO = new CompanyDAO(connection);
            // Company c = compDAO.get(rs.getInt("company_id"));
            for (Company c : cacheCompanies) {
                if (c.getId() == rs.getLong("company_id")) {
                    compuTemp.setComp(c);
                }
            }
            result.add(compuTemp);

        }
        rs.close();
        connection.closeConnect(COMPUTER_DB_NAME);
        return result;
    }

    public int getSizeTable() {
        return 0;
    }
}
