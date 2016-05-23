package com.excilys.dao.spring;


import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;



import com.excilys.dao.DAO;
import com.excilys.mapper.Mapper;
import com.excilys.model.Company;


public class SpringCompanyDAO implements DAO<Company>{

    private final Logger slf4jLogger = LoggerFactory
            .getLogger(SpringCompanyDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Return the List of all the companies.
     * @return The company list
     */
    @SuppressWarnings("unchecked")
    public List<Company> getAll() {
        String sql = "SELECT * FROM `company` ;";

        return Mapper.resultToListCompanySpring(this.jdbcTemplate.queryForList(sql));
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
        String sql = "SELECT * FROM `company` WHERE id= ? ;";
        
        return (Company) this.jdbcTemplate.queryForObject(sql, new Object[] {id}, Company.class);
    }
    
    /**
     * Delete the company and all the computers linkeds to the company.
     * @param idCompa The id of the company who needs to be deleted
     */
    public void delete(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        String sql = "DELETE FROM `company` WHERE `id` = ? ;";
        
        jdbcTemplate.update(sql, new Object[] {id});
    }

}
