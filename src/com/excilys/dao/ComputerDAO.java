package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private JDBCTool toolConnexion;
    private static List<Company> cacheCompanies = null;

    public void setConnexion(JDBCTool c) {
        if (c == null) {
            throw new IllegalArgumentException("Tool is null");
        }
        toolConnexion = c;
        // toolConnexion.linkToMySql();
    }

    public JDBCTool getConnexion() {
        return toolConnexion;
    }

    private List<Company> initCompanies() {
        if (cacheCompanies == null) {
            CompanyDAO comA = new CompanyDAO(toolConnexion);
            cacheCompanies = comA.getAll();
        }
        return cacheCompanies;
    }

    @Override
    public List<Computer> getAll() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        List<Computer> result = null;
        try {
            initCompanies();
            toolConnexion.connect(COMPUTER_DB_NAME);
            // Execute a query
            /*
             * PreparedStatement stmt =
             * connection.getConnection(COMPUTER_DB_NAME).
             * 
             * // SELECT c.id, c.name, c.introduced, c.discontinued, com.name
             * FROM // computer c INNER JOIN company com ON com.id = company_id
             * String sql = ResultSet rs = stmt.executeQuery(sql);
             */
            String sql = "SELECT * FROM `computer`;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            result = new ArrayList<>();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column : id | name | introduced | discontinued |
                // company_id
                Computer compuTemp = new Computer();
                compuTemp.setId(rs.getLong("id"));
                compuTemp.setName(rs.getString("name"));
                compuTemp.setIntro(rs.getTimestamp("introduced"));
                compuTemp.setDisco(rs.getTimestamp("discontinued"));

                // CompanyDAO compDAO = new CompanyDAO(toolConnexion);
                // Company c = compDAO.get(rs.getInt("company_id"));
                for (Company c : cacheCompanies) {
                    if (c.getId() == rs.getLong("company_id")) {
                        compuTemp.setComp(c);
                    }
                }
                result.add(compuTemp);

            }
            rs.close();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return result;
    }

    @Override
    public Computer get(long idComp) {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }

        Computer compuTemp = null;
        try {
            initCompanies();
            toolConnexion.connect(COMPUTER_DB_NAME);
            // Execute a query

            /*
             * Statement stmt = toolConnexion.getConnection(COMPUTER_DB_NAME)
             * .createStatement();
             * 
             * String sql = "SELECT * FROM `computer` WHERE id=" + idComp + ";";
             * ResultSet rs = stmt.executeQuery(sql);
             */

            String sql = "SELECT * FROM `computer` WHERE id = ? ;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            stmt.setLong(1, idComp);
            ResultSet rs = stmt.executeQuery();

            compuTemp = null;

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
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
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
        /*
         * String sql = "SELECT * FROM `computer` WHERE id = ? ;";
         * PreparedStatement stmt =
         * toolConnexion.getConnection(COMPUTER_DB_NAME).prepareStatement(sql);
         * stmt.setLong(1, idComp); ResultSet rs = stmt.executeQuery();
         */
        try {
            toolConnexion.connect(COMPUTER_DB_NAME);

            String sql = "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES (?,?,?,?);";

            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            /*
             * String sql =
             * "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES ('"
             * + comp.getName() + "', '" + comp.getIntro() + "', '" +
             * comp.getDisco() + "'," + comp.getComp().getId() + ");";
             * 
             * sql = sql.replaceAll("'null'", "NULL"); if (comp.getId() == 0) {
             * sql = sql.replaceAll("0\\);", "NULL\\);"); }
             */
            stmt.setString(1, comp.getName());
            stmt.setTimestamp(2, comp.getIntro());
            stmt.setTimestamp(3, comp.getDisco());
            stmt.setObject(4, comp.getComp().getId());

            stmt.executeUpdate();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
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
        try {
            toolConnexion.connect(COMPUTER_DB_NAME);
            String sql = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ?, WHERE `computer`.`id` = ?;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            stmt.setString(1, comp.getName());
            stmt.setTimestamp(2, comp.getIntro());
            stmt.setTimestamp(2, comp.getDisco());
            stmt.setObject(3, comp.getComp().getId());
            stmt.setLong(4, comp.getId());
            /*
             * sql = sql.replaceAll("'null'", "NULL"); if (comp.getId() == 0) {
             * sql = sql.replaceAll("0\\);", "NULL\\);"); }
             */
            stmt.executeUpdate();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
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
        try {
            toolConnexion.connect(COMPUTER_DB_NAME);

            String sql = "DELETE FROM `computer` WHERE `id` = ? ;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            stmt.setLong(1, comp);
            stmt.executeUpdate();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
    }

    public List<Computer> getSet(int low, int height) {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        List<Computer> result = null;
        try {
            initCompanies();
            toolConnexion.connect(COMPUTER_DB_NAME);

            String sql = "SELECT * FROM `computer` LIMIT ?,? ;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);
            stmt.setInt(1, low);
            stmt.setInt(2, height);

            ResultSet rs = stmt.executeQuery();

            result = new ArrayList<>();

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
            toolConnexion.closeConnect(COMPUTER_DB_NAME);
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return result;
    }

    public long getSizeTable() {
        if (toolConnexion == null) {
            throw new IllegalStateException("Pas de connexion tool");
        }
        try {
            toolConnexion.connect(COMPUTER_DB_NAME);

            String sql = "SELECT COUNT(*) FROM `computer`;";
            PreparedStatement stmt = toolConnexion.getConnection(
                    COMPUTER_DB_NAME).prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            toolConnexion.closeConnect(COMPUTER_DB_NAME);

            while (rs.next()) {
                return rs.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return 0;
    }
}
