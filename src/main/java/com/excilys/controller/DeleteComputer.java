package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.service.HeavyComputerDAO;


@Controller
@RequestMapping("/delete")
public class DeleteComputer {

    /**
     * The post version of delete a computer.
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    @RequestMapping(method = RequestMethod.POST)
    public String deleteComputer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String params = request.getParameterValues("selection")[0]; 
     
        String result[] = (params.split(",")); 

        HeavyComputerDAO work = new HeavyComputerDAO();
        for (String c : result) {
            try {
                work.deleteComputer(Long.parseLong(c));
            } catch (NumberFormatException e) {
                response.sendRedirect("/ComputerDatabaseMaven/dash");
                return "redirect:dashboard";
            }
        }
        
        //response.sendRedirect("/ComputerDatabaseMaven/dash?retourn=2");
        return "dashboard";
    }
}
