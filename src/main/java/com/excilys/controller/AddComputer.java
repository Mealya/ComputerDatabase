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

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.HeavyComputerDAO;


public class AddComputer extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(AddComputer.class);
    private static final long serialVersionUID = 1818795394032861086L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();

        CompanyDAO compt = new CompanyDAO(tool);
        
        List<Company> companies = compt.getAll();
        request.setAttribute("companies", companies);
        
        request.setAttribute("added", 2);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
                .forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Computer computer = new Computer();
        
        computer.setName(request.getParameter("computerName"));
       
        Timestamp time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(request.getParameter("introduced"));
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            slf4jLogger.info("Bad entry for introduced : " + e.getMessage());
        }
        computer.setIntro(time);
        
        time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(request.getParameter("discontinued"));
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            slf4jLogger.info("Bad entry for discovered : " + e.getMessage());
        }
        computer.setDisco(time);

        Company compTemp = null;
        long idCompa = Long.parseLong(request.getParameter("companyId"));
        if (idCompa != 0) {
            compTemp = new Company();
            compTemp.setId(idCompa);
            computer.setComp(compTemp);
        }

        
        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();
        ComputerDAO compDAO = new ComputerDAO(tool);
        HeavyComputerDAO serv = new HeavyComputerDAO(compDAO);
        serv.createComputer(computer);
        
        
        
        
        
        CompanyDAO compt = new CompanyDAO(tool);
        
        List<Company> companies = compt.getAll();
        request.setAttribute("companies", companies);
        
        request.setAttribute("added", 1);
        this.getServletContext().getRequestDispatcher("/vues/raw/views/addComputer.jsp")
        .forward(request, response);
        
    }
}

