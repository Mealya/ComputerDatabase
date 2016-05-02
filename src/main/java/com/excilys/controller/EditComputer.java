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

public class EditComputer extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory
            .getLogger(EditComputer.class);
    private static final long serialVersionUID = -6582045182381493078L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            slf4jLogger.info("Bad parameter for id");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (id <= 0) {
            slf4jLogger.info("Request with id : " + id);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        request.setAttribute("id", id);

        HeavyComputerDAO work = new HeavyComputerDAO();

        HeavyCompanyDAO workCompa = new HeavyCompanyDAO();

        List<Company> companies = workCompa.getCompanies();
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
        Computer computer = null;

        computer = Validator.validateComputerEdit(request.getParameter("id"), request.getParameter("computerName"), request.getParameter("introduced"), 
                request.getParameter("discontinued"), request.getParameter("companyId"));
        if (computer != null) {
            HeavyComputerDAO serv = new HeavyComputerDAO();
            serv.createComputer(computer);
        } else {
            slf4jLogger.warn("Fail to edit a computer");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        response.sendRedirect("/ComputerDatabaseMaven/dash?return=1");
    }
}
