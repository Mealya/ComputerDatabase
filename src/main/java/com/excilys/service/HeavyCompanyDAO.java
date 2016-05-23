package com.excilys.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.ExceptionDAO;
import com.excilys.dao.spring.SpringCompanyDAO;
import com.excilys.dao.spring.SpringComputerDAO;
import com.excilys.database.SpringDataSource;
import com.excilys.model.Company;
import com.excilys.utils.EasyConnection;

public class HeavyCompanyDAO {

    private SpringCompanyDAO compaDB;
    private SpringComputerDAO compuDB;
    private PlatformTransactionManager transactionManager;

    
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * Create a service linked to the CompanyDAO.
     */
    public HeavyCompanyDAO() {
        compaDB = (SpringCompanyDAO) SpringDataSource.getContext().getBean("SpringCompanyDAO");
        compuDB = (SpringComputerDAO) SpringDataSource.getContext().getBean("SpringComputerDAO");
    }

    /**
     * To have the list of the companies.
     * @return The List who represents the companies
     */
    public List<Company> getCompanies() {
        List<Company> result = null;
        result = compaDB.getAll();
        return result;
    }

    /**
     * To have a company linked to an id.
     * @param idCompu The id of the company
     * @return The Company found
     */
    public Company getCompany(long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        Company result = null;
        result = compaDB.get(idCompu);
        return result;
    }
    
    /**
     * Nothing now.
     * @param c A company
     */
    @Deprecated
    public void createCompany(Company c) {throw new UnsupportedOperationException("Not implemented");

    }

    /**
     * Nothing now.
     * @param c A company
     */
    @Deprecated
    public void updateCompany(Company c) {

    }

    /**
     * Call the DAO to delete the company and all the computers linkeds to the company.
     * @param idCompa The id of the company who needs to be deleted
     */
    
    public void deleteCompany(long idCompa) {
        if (idCompa < 0) {
            throw new IllegalArgumentException("Id non valide");
        }

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
            compuDB.deleteWithCompany(idCompa);
            compaDB.delete(idCompa);
            transactionManager.commit(status);
        } catch (DataAccessException | ExceptionDAO e) {
            transactionManager.rollback(status);
            throw new ExceptionService(e.getMessage());
        }        
    }
    
    
    /**
     * Nothing now.
     * @param low First parameter of the LIMIT
     * @param height Second parameter of the LIMIT
     * @return Nothing
     */
    @Deprecated
    public List<Company> getSetCompany(int low, int height) {
        return null;
    }
}
