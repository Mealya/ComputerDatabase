package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.service.ComputerService;


@Controller
public class DeleteComputer {

    /**
     * The post version of delete a computer.
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deleteComputer(HttpServletRequest request) throws IOException {
        String params = request.getParameterValues("selection")[0]; 
     
        String result[] = (params.split(",")); 

        ComputerService work = new ComputerService();
        for (String c : result) {
            try {
                work.deleteComputer(Long.parseLong(c));
            } catch (NumberFormatException e) {
                //response.sendRedirect("/ComputerDatabaseMaven/dash");
                return new ModelAndView("redirect:/dash");
            }
        }
        
        //response.sendRedirect("/ComputerDatabaseMaven/dash?retourn=2");
        //request.setAttribute("retourn", 2);
        return new ModelAndView("redirect:/dash?retourn=2");
    }
}
