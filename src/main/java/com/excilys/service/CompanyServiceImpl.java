package com.excilys.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.excilys.model.Company;
import com.excilys.repository.CompanyRepository;
import com.excilys.repository.ComputerRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyRepository companyRep;
    
    @Resource
    private ComputerRepository computerRep;

    @Override
    @Transactional
    public List<Company> getCompanies() {
        return companyRep.findAll();
    }

    @Override
    @Transactional
    public Company getCompany(Long idCompa) {
        if (idCompa < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        return companyRep.findOne(idCompa);
    }
    
    @Override
    @Transactional(rollbackFor=IllegalArgumentException.class)
    public void deleteCompany(long idCompa) {
        if (idCompa < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        
        Company temp = companyRep.findOne(idCompa);
        
        if (temp == null) {
            throw new IllegalArgumentException("Id non valide");
        }
        companyRep.delete(idCompa);
    }
    
    
    @Deprecated
    public List<Company> getSetCompany(int low, int height) {
        return null;
    }
    
    /**
     * Nothing now.
     */
    @Deprecated
    public void createCompany(Company c) {throw new UnsupportedOperationException("Not implemented");

    }

    /**
     * Nothing now.
     */
    @Deprecated
    public void updateCompany(Company c) {

    }
}
