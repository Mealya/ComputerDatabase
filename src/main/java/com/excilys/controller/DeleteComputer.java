package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.HeavyComputerDAO;



public class DeleteComputer extends HttpServlet {

    private static final long serialVersionUID = -3950022894376611585L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String params = request.getParameterValues("selection")[0]; 
     
        String result[] = (params.split(",")); 

        HeavyComputerDAO work = new HeavyComputerDAO();
        for (String c : result) {
            try {
                work.deleteComputer(Long.parseLong(c));
            } catch (NumberFormatException e) {
                response.sendRedirect("/ComputerDatabaseMaven/dash");
                return;
            }
        }
        
        response.sendRedirect("/ComputerDatabaseMaven/dash?retourn=2");
    }
}
