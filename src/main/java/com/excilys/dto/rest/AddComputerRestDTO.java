package com.excilys.dto.rest;

import java.util.List;

import com.excilys.dto.AddComputerDTO;
import com.excilys.model.Company;

public class AddComputerRestDTO {

    private AddComputerDTO computers;
    private List<Company> company;
    /**
     * @return the computers
     */
    public AddComputerDTO getComputers() {
        return computers;
    }
    /**
     * @param computers the computers to set
     */
    public void setComputers(AddComputerDTO computers) {
        this.computers = computers;
    }
    /**
     * @return the company
     */
    public List<Company> getCompany() {
        return company;
    }
    /**
     * @param company the company to set
     */
    public void setCompany(List<Company> company) {
        this.company = company;
    }

}
