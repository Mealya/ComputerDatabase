package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class CompanyDAO implements DAO<Company>{

    private Connection connect;

    public CompanyDAO(Connection c) throws SQLException {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }

        connect = c;
    }

    public List<Company> getAll() throws SQLException {
        //Execute a query
        Statement stmt = connect.createStatement();

        String sql = "SELECT * FROM `company` ";
        ResultSet rs = stmt.executeQuery(sql);

        List<Company> result = new ArrayList<>();

        //Extract data from result set
        while (rs.next()) {
            //Retrieve by column : id | name | introduced | discontinued | company_id
            int id = rs.getInt("id");
            String name = rs.getString("name");


            result.add(new Company(id, name));

        }
        rs.close();
        return result;
    }

    @Override
    public Company get(long id) throws SQLException {
        if (connect == null) {
            throw new IllegalStateException("Pas de connexion");
        }
        //Execute a query
        Statement stmt = connect.createStatement();

        String sql = "SELECT * FROM `company` WHERE id=" + id +";";
        ResultSet rs = stmt.executeQuery(sql);

        Company compa = null;

        //Extract data from result set
        while (rs.next()) {
            compa = new Company();
            compa.setId(rs.getInt("id"));
            compa.setName(rs.getString("name"));


        }
        rs.close();
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
