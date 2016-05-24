package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.validator.Validator;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.HeavyCompanyDAO;
import com.excilys.service.HeavyComputerDAO;

@Controller
@RequestMapping("/add")
public class AddComputer {

    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    
    /**
     * The get version of add a computer.
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    
    @RequestMapping(method = RequestMethod.GET)
    public String addComputerView(ModelMap model, HttpServletRequest request)
            throws IOException {

        HeavyCompanyDAO workCompt = new HeavyCompanyDAO();
        
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
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(ModelMap model, HttpServletRequest request) throws IOException {
        Computer computer = null;
        
        computer = Validator.validateComputerAdd(request.getParameter("computerName"), request.getParameter("introduced"), 
                request.getParameter("discontinued"), request.getParameter("companyId"));
        
        if (computer != null) {
            HeavyComputerDAO serv = new HeavyComputerDAO();
            serv.createComputer(computer);
        } else {
            slf4jLogger.error("Fail to add a computer");
            return "addComputer";
        }
              
        HeavyCompanyDAO workCompt = new HeavyCompanyDAO();  
        List<Company> companies = workCompt.getCompanies();
        model.addAttribute("companies", companies);
        
        model.addAttribute("added", 1);
        /*request.setAttribute("added", 1);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
        .forward(request, response);*/
        return "addComputer";
        
    }
}

