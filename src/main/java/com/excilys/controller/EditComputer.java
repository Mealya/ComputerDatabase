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
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping("/edit")
public class EditComputer {

    private final Logger slf4jLogger = LoggerFactory
            .getLogger(EditComputer.class);

    /**
     * The get version of edit a computer.
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    @RequestMapping(method = RequestMethod.GET)
    public String editComputerView(ModelMap model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            slf4jLogger.info("Bad parameter for id : " + request.getParameter("id"));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:dashboard";
        }
        if (id <= 0) {
            slf4jLogger.info("Request with id : " + id);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:dashboard";
        }

        model.addAttribute("id", id);

        ComputerService work = new ComputerService();

        CompanyService workCompa = new CompanyService();

        List<Company> companies = workCompa.getCompanies();
        model.addAttribute("companies", companies);
        Computer temp = work.getComputer(id);
        if (temp.getName() != null) {
            model.addAttribute("name", temp.getName());
        }
        if (temp.getIntro() != null) {
            model.addAttribute("intro", temp.getIntro());
        }
        if (temp.getDisco() != null) {
            model.addAttribute("disco", temp.getDisco());
        }
        if (temp.getComp() != null) {
            model.addAttribute("idCompa", temp.getComp().getId());
        }

        /*this.getServletContext()
                .getRequestDispatcher("/vues/raw/views/editComputer.jsp")
                .forward(request, response);*/
        return "editComputer";
    }

    /**
     * The post version of edit a computer.
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws ServletException Error with servlet
     * @throws IOException Error with stream
     */
    @RequestMapping(method = RequestMethod.POST)
    public String editComputer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Computer computer = null;

        computer = Validator.validateComputerEdit(request.getParameter("id"), request.getParameter("computerName"), request.getParameter("introduced"), 
                request.getParameter("discontinued"), request.getParameter("companyId"));
        if (computer != null) {
            ComputerService serv = new ComputerService();
            serv.createComputer(computer);
        } else {
            slf4jLogger.warn("Fail to edit a computer");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return "dashboard";
        }


        //response.sendRedirect("/ComputerDatabaseMaven/dash?retourn=1");
        return "dashboard";
    }
}
