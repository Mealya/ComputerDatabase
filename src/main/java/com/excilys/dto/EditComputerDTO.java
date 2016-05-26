package com.excilys.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class EditComputerDTO {

    @NotNull
    private String id;
    
    @Size(min=1, max=45)
    private String computerName;
    
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)|((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])|^$")
    private String discontinued;
    
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)|((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])|^$")
    private String introduced;
    
    @NotNull
    private String companyId;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the computerName
     */
    public String getComputerName() {
        return computerName;
    }

    /**
     * @param computerName the computerName to set
     */
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    /**
     * @return the discontinued
     */
    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued the discontinued to set
     */
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * @return the introduced
     */
    public String getIntroduced() {
        return introduced;
    }

    /**
     * @param introduced the introduced to set
     */
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * @return the companyId
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}