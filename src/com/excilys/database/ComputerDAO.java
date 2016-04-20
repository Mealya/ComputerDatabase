package com.excilys.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Computer;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class ComputerDAO implements DAO<Computer>{

    private Connection connect;

    public void setConnexion(Connection c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }
        connect = c;
    }

    public Connection getConnexion() {
        return connect;
    }
    
    @Deprecated
    private String printComputers() throws SQLException {
        //Execute a query
        System.out.println("Creating statement...");
        Statement stmt = connect.createStatement();

        String sql = "SELECT * FROM `computer` ";
        ResultSet rs = stmt.executeQuery(sql);
        StringBuilder buff = new StringBuilder();
        buff.append("\n ======= \n Print computers \n ======= \n");
        //Extract data from result set
        while (rs.next()) {
            //Retrieve by column : id | name | introduced | discontinued | company_id
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Timestamp first = rs.getTimestamp("introduced");
            Timestamp last = rs.getTimestamp("discontinued");
            int compID = rs.getInt("company_id");


            buff.append("ID: " + id + ", Nom: " + name 
                    + ", Introduit: " + first + ", Stoppé: " + last +", ID compagnie: " + compID + "\n");
        }
        rs.close();
        return buff.toString();
    }

    @Override
    public List<Computer> getAll() throws SQLException {
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        //Execute a query
        Statement stmt = connect.createStatement();

        // SELECT c.id, c.name, c.introduced, c.discontinued, com.name FROM computer c INNER JOIN company com ON com.id = company_id
        String sql = "SELECT * FROM `computer` ;";
        ResultSet rs = stmt.executeQuery(sql);

        List<Computer> result = new ArrayList<>();

        //Extract data from result set
        while (rs.next()) {
            //Retrieve by column : id | name | introduced | discontinued | company_id
            long id = rs.getInt("id");
            String name = rs.getString("name");
            Timestamp first = rs.getTimestamp("introduced");
            Timestamp last = rs.getTimestamp("discontinued");
            int compID = rs.getInt("company_id");

            result.add(new Computer(id, name, first, last, compID));

        }
        rs.close();
        return result;
    }

    @Override
    public Computer get(long idComp) throws SQLException {
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        //Execute a query
        Statement stmt = connect.createStatement();

        String sql = "SELECT * FROM `computer` WHERE id=" + idComp+";";
        ResultSet rs = stmt.executeQuery(sql);

        Computer result = null;

        //Extract data from result set
        while (rs.next()) {
            long id = rs.getInt("id");
            String name = rs.getString("name");
            Timestamp first = rs.getTimestamp("introduced");
            Timestamp last = rs.getTimestamp("discontinued");
            int compID = rs.getInt("company_id");

            result = new Computer(id, name, first, last, compID);

        }
        rs.close();
        return result;
    }

    @Override
    public void create(Computer comp) throws SQLException {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        Statement stmt = connect.createStatement();

        String sql = "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES ('"
                +comp.getName()+"', '"+comp.getIntro()+"', '"+comp.getDisco()+"',"+comp.getCompId()+")";
        stmt.executeUpdate(sql);

    }

    @Override
    public void update(Computer comp) throws SQLException {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        Statement stmt = connect.createStatement();

        String sql = "UPDATE `computer` SET `name` = '"+ comp.getName() +"', `introduced` = '"
                + comp.getIntro()+"', `discontinued` = '"+ comp.getDisco()
                +"', `company_id` = '"+ comp.getCompId()+"' WHERE `computer`.`id` = "+ comp.getId()+";";
        stmt.executeUpdate(sql);
    }

    @Override
    public void delete(long comp) throws SQLException {
        if (comp < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        Statement stmt = connect.createStatement();

        String sql = "DELETE FROM `computer` WHERE `id` = "+comp+";";
        stmt.executeUpdate(sql);
    }

    public List<Computer> getSet(int low, int height) throws SQLException {
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        //Execute a query
        Statement stmt = connect.createStatement();

        String sql = "SELECT * FROM `computer` LIMIT "+low+","+height+";";
        ResultSet rs = stmt.executeQuery(sql);

        List<Computer> result = new ArrayList<>();

        //Extract data from result set
        while (rs.next()) {
            //Retrieve by column : id | name | introduced | discontinued | company_id
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Timestamp first = rs.getTimestamp("introduced");
            Timestamp last = rs.getTimestamp("discontinued");
            int compID = rs.getInt("company_id");

            result.add(new Computer(id, name, first, last, compID));

        }
        rs.close();
        return result;
    }
}

