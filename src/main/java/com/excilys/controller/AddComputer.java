package com.excilys.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.validator.Validator;
import com.excilys.dto.AddComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;


import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class AddComputer {

    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    
    @Autowired
    private CompanyService workCompt;
    
    @Autowired
    private ComputerService workCompu;
    
    /**
     * The get version of add a computer.
     */
    @RequestMapping(value="addComputerForm", method = RequestMethod.GET)
    public String addComputerView(ModelMap model)
            throws IOException {

        List<Company> companies = workCompt.getCompanies();
        model.addAttribute("companies", companies);
        
        model.addAttribute("added", 2);

        return "addComputer";
    }
    
    /**
     * The post version of add a computer.
     */
    @RequestMapping(value="addComputer", method = RequestMethod.POST)
    public String addComputer(@Valid AddComputerDTO addcomputerdto, BindingResult bindingResult, ModelMap model) throws IOException {
        List<Company> companies = workCompt.getCompanies();
        model.addAttribute("companies", companies);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("fail", 1);
            return "addComputer";
        }
        
        Computer computerToAdd = Validator.validateComputerAdd(addcomputerdto.getComputerName(), addcomputerdto.getIntroduced(), addcomputerdto.getDiscontinued(), addcomputerdto.getCompanyId());
        
        if (computerToAdd != null) {
            workCompu.createComputer(computerToAdd);
        } else {
            slf4jLogger.error("Fail to add a computer");
            model.addAttribute("fail", 1);
            return "addComputer";
        }
        model.addAttribute("added", 1);

        return "addComputer";
        
    }
}

