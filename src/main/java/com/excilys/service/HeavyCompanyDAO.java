package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Company;

public class HeavyCompanyDAO {

    private CompanyDAO compaDB;

    public HeavyCompanyDAO() {
        JDBCTool tool = new JDBCTool();
        //tool.linkToMySql();
        compaDB = new CompanyDAO(tool);
    }

    public List<Company> getCompanies() {
        List<Company> result = null;
        result = compaDB.getAll();
        return result;
    }

    public Company getComputer(long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        Company result = null;
        result = compaDB.get(idCompu);
        return result;
    }
    
    @Deprecated
    public void createCompany(Company c) {

    }

    @Deprecated
    public void updateCompany(Company c) {

    }

    @Deprecated
    public void deleteCompany(long c) {

    }

    @Deprecated
    public List<Company> getSetCompany(int low, int height) {
        return null;
    }
}
