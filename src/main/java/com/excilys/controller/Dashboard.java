package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;
import com.excilys.service.HeavyComputerDAO;

public class Dashboard extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(Dashboard.class);
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int size = 15;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for page");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        if (request.getParameter("size") != null) {
            try {
                page = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for size");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        String search = null;
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        String nbComputers;

        HeavyComputerDAO workingDB = new HeavyComputerDAO();
        
        long computersLong = workingDB.getSizeTable();
        nbComputers = String.valueOf(computersLong);
        if (computersLong < (page - 1) * 15) {
            slf4jLogger.info("Bad parameter for size");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        List<Computer> computers = null;

        int low = (page * size) - size;
        computers = workingDB.getSetComputer(low, size);
        if (request.getParameter("search") != null) {
            for (int i = 0; i < computers.size(); i++) {
                if (!computers.get(i).getName().equals(search)) {
                    if (computers.get(i).getComp() != null) {
                        if (!computers.get(i).getComp().getName().equals(search)) {
                            computers.remove(i);
                            i--;
                        }
                    } 
                    else {
                        computers.remove(i);
                        i--;
                    }
                }
            }
        }


        request.setAttribute("computers", computers);
        request.setAttribute("nbComputers", nbComputers);

        this.getServletContext()
                .getRequestDispatcher("/vues/raw/views/dashboard.jsp")
                .forward(request, response);
    }
}
