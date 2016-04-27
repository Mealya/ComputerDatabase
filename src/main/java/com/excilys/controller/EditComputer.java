package com.excilys.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.HeavyComputerDAO;

public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = -6582045182381493078L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // TODO check fail cast
        request.setAttribute("id", id);

        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();

        ComputerDAO compt = new ComputerDAO(tool);
        HeavyComputerDAO work = new HeavyComputerDAO(compt);
        CompanyDAO compa = new CompanyDAO(tool);

        List<Company> companies = compa.getAll();
        request.setAttribute("companies", companies);
        Computer temp = work.getComputer(id);
        if (temp.getName() != null) {
            request.setAttribute("name", temp.getName());
        }
        if (temp.getIntro() != null) {
            request.setAttribute("intro", temp.getIntro());
        }
        if (temp.getDisco() != null) {
            request.setAttribute("disco", temp.getDisco());
        }
        if (temp.getComp() != null) {
            request.setAttribute("idCompa", temp.getComp().getId());
        }

        this.getServletContext()
                .getRequestDispatcher("/vues/raw/views/editComputer.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
Computer computer = new Computer();
        

        computer.setId(Long.parseLong(request.getParameter("id")));
        computer.setName(request.getParameter("computerName"));
       
        Timestamp time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(request.getParameter("introduced"));
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {
          
        }
        computer.setIntro(time);
        
        time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(request.getParameter("discontinued"));
            time = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {
          
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
        serv.updateComputer(computer);


        response.sendRedirect("/ComputerDatabaseMaven/dash?return=1");
    }
}
