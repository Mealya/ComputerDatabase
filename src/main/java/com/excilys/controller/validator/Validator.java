package com.excilys.controller.validator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class Validator {

    private final static Logger slf4jLogger = LoggerFactory.getLogger(Validator.class);
    
    public static Computer validateComputerAdd(String name, String intro, String disco, String company) {
        Computer computer = new Computer();
        
        if (name == null) {
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
                slf4jLogger.warn("Bad entry for introduced : " + e.getMessage());
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
                slf4jLogger.warn("Bad entry for discovered : " + e.getMessage());
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
        } catch (NumberFormatException e){
            slf4jLogger.warn("Bad entry for company id : " + e.getMessage());
            return null;
        }
        

        return computer;
    }

    public static Computer validateComputerEdit(String id, String name, String intro, String disco, String company) {
        Computer compu = validateComputerAdd(name, intro, disco, company);
        
        if (compu == null) {
            return null;
        } else {         
            try {
                compu.setId(Long.parseLong(id));
            } catch (NumberFormatException e) {
                slf4jLogger.warn("Bad entry for company id : " + e.getMessage());
                return null;
            }
            return compu;
        }
    }
}
