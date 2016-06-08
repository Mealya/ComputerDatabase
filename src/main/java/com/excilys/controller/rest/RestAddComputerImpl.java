package com.excilys.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.controller.AddComputer;
import com.excilys.controller.dtomapper.Mappator;
import com.excilys.controller.rest.RestAddComputer;
import com.excilys.dto.AddComputerDTO;
import com.excilys.dto.rest.AddComputerRestDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping("/rest/")
public class RestAddComputerImpl implements RestAddComputer {
    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    
    @Autowired
    private CompanyService workCompt;
    
    @Autowired
    private ComputerService workCompu;
    

    @RequestMapping(value="addComputerForm", method = RequestMethod.GET)
    public ResponseEntity<?> addComputerView() {
        List<Company> companies = workCompt.getCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }
    

    @RequestMapping(value="addComputer", method = RequestMethod.POST)
    public ResponseEntity<?> addComputer(HttpServletRequest request) {
        Computer computerToAdd = Mappator.computerToAdd(request.getParameter("computerName"),
                                                        request.getParameter("introduced"),
                                                        request.getParameter("discontinued"),
                                                        request.getParameter("companyId"));
        //FIXME : DTO version
        //Computer computerToAdd = Mappator.computerToAdd(addcomputerdto.getComputerName(), addcomputerdto.getIntroduced(), addcomputerdto.getDiscontinued(), addcomputerdto.getCompanyId());
        
        if (computerToAdd != null) {
            workCompu.createComputer(computerToAdd);
        } else {
            slf4jLogger.error("Fail to add a computer");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to add a computer");
        }
        return ResponseEntity.status(HttpStatus.OK).body(computerToAdd);
    }
}

