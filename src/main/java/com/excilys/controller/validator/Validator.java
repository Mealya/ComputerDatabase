package com.excilys.controller.validator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    
    /**
     * Validate the parameters for a new computer.
     * @param name Parameter of the computer
     * @param intro Parameter of the computer
     * @param disco Parameter of the computer
     * @param company Parameter of the computer
     * @return The computer who need to be added
     */
    public static Computer validateComputerAdd(String name, String intro, String disco, String company) {
        Computer computer = new Computer();
        
        if (name == null) {
            LOGGER.warn("Bad entry for name : null ");
            return null;
        } else {
            computer.setName(name);
        }
        
        Timestamp time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(intro);
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            if (!intro.equals("")) {
                LOGGER.warn("Bad entry for introduced : " + e.getMessage());
            }
        }
        computer.setIntro(time);
        
        time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(disco);
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            if (!disco.equals("")) {
                LOGGER.warn("Bad entry for discovered : " + e.getMessage());
            }
        }
        computer.setDisco(time);

        Company compTemp = null;
        try {
            long idCompa = Long.parseLong(company);
            if (idCompa != 0) {
                compTemp = new Company();
                compTemp.setId(idCompa);
                computer.setComp(compTemp);
            }
        } catch (NumberFormatException e) {
            LOGGER.warn("Bad entry for company id : " + e.getMessage());
            return null;
        }
        

        return computer;
    }

    /**
     * Validate the parameters for edit a computer.
     * @param id  Parameter of the computer
     * @param name Parameter of the computer
     * @param intro Parameter of the computer
     * @param disco Parameter of the computer
     * @param company Parameter of the computer
     * @return The computer who need to be edited
     */
    public static Computer validateComputerEdit(String id, String name, String intro, String disco, String company) {
        Computer compu = validateComputerAdd(name, intro, disco, company);
        
        if (compu == null) {
            return null;
        } else {         
            try {
                compu.setId(Long.parseLong(id));
            } catch (NumberFormatException e) {
                LOGGER.warn("Bad entry for company id : " + e.getMessage());
                return null;
            }
            return compu;
        }
    }
    
    public static Computer validateCompany(Computer c, String company) {
        Company compTemp = null;
        try {
            long idCompa = Long.parseLong(company);
            if (idCompa != 0) {
                compTemp = new Company();
                compTemp.setId(idCompa);
                c.setComp(compTemp);
            }
        } catch (NumberFormatException e) {
            LOGGER.warn("Bad entry for company id : " + e.getMessage());
            return null;
        }
        return c;
    }
    
}
