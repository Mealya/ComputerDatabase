package com.excilys.service;

import java.util.List;

import com.excilys.model.Company;

public interface CompanyService {

    /**
     * To have the list of the companies.
     * @return The List who represents the companies
     */
    public List<Company> getCompanies() ;

    /**
     * To have a company linked to an id.
     * @param idCompu The id of the company
     * @return The Company found
     */
    public Company getCompany(Long idCompa) ;

    /**
     * Call the DAO to delete the company and all the computers linkeds to the company.
     * @param idCompa The id of the company who needs to be deleted
     */
    public void deleteCompany(long idCompa);
    
}
