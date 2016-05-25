package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.validator.Validator;
import com.excilys.dto.AddComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

import org.springframework.web.bind.annotation.ModelAttribute;  

import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class AddComputer {

    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    
    /**
     * The get version of add a computer.
     */
    
    @RequestMapping(method = RequestMethod.GET)
    public String addComputerView(ModelMap model, HttpServletRequest request)
            throws IOException {
        model.addAttribute("addcomputerdto", new AddComputerDTO());
        
        CompanyService workCompt = new CompanyService();
        
        List<Company> companies = workCompt.getCompanies();
        model.addAttribute("companies", companies);
        
        model.addAttribute("added", 2);
        /*request.setAttribute("added", 2);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
                .forward(request, response);*/
        return "addComputer";
    }
    
    /**
     * The post version of add a computer.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(@ModelAttribute("addcomputerdto") @Valid AddComputerDTO addcomputerdto, BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws IOException {
        /*if (bindingResult.hasErrors()) {
            return "redirect:addComputer";
        }*/
        
        Computer computerToAdd = Validator.validateComputerAdd(addcomputerdto.getName(), addcomputerdto.getIntro(), addcomputerdto.getDisco(), addcomputerdto.getComp());
        
        if (computerToAdd != null) {
            ComputerService serv = new ComputerService();
            serv.createComputer(computerToAdd);
        } else {
            slf4jLogger.error("Fail to add a computer");
            return "addComputer";
        }
              
        CompanyService workCompt = new CompanyService();  
        List<Company> companies = workCompt.getCompanies();
        model.addAttribute("companies", companies);
        
        model.addAttribute("added", 1);
        /*request.setAttribute("added", 1);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
        .forward(request, response);*/
        return "addComputer";
        
    }
}

