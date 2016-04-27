package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Computer;
import com.excilys.service.HeavyComputerDAO;

public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        int page = 1;
        int size = 15;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null) {
            size = Integer.parseInt((String) request.getParameter("size"));
        }

        String nbComputers;

        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();

        ComputerDAO compt = new ComputerDAO(tool);
        HeavyComputerDAO workingDB = new HeavyComputerDAO(compt);

        nbComputers = String.valueOf(compt.getSizeTable());

        List<Computer> computers = null;

        int low = (page * size) - size;
        computers = workingDB.getSetComputer(low, size);

        request.setAttribute("computers", computers);
        request.setAttribute("nbComputers", nbComputers);
        this.getServletContext()
                .getRequestDispatcher("/vues/raw/views/dashboard.jsp")
                .forward(request, response);
    }
}
