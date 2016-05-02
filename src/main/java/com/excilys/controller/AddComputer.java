package com.excilys.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.controller.validator.Validator;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.HeavyCompanyDAO;
import com.excilys.service.HeavyComputerDAO;


public class AddComputer extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    private static final long serialVersionUID = 1818795394032861086L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HeavyCompanyDAO workCompt = new HeavyCompanyDAO();
        
        List<Company> companies = workCompt.getCompanies();
        request.setAttribute("companies", companies);
        
        request.setAttribute("added", 2);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
                .forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Computer computer = null;
        
        computer = Validator.validateComputerAdd(request.getParameter("computerName"), request.getParameter("introduced"), 
                request.getParameter("discontinued"), request.getParameter("companyId"));
        
        if (computer != null) {
            HeavyComputerDAO serv = new HeavyComputerDAO();
            serv.createComputer(computer);
        } else {
            slf4jLogger.warn("Fail to add a computer");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
              
        HeavyCompanyDAO workCompt = new HeavyCompanyDAO();  
        List<Company> companies = workCompt.getCompanies();
        request.setAttribute("companies", companies);
        
        request.setAttribute("added", 1);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
        .forward(request, response);
        
    }
}

