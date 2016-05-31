package com.excilys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.utils.OrderType;

@Controller
@Configuration
public class Dashboard {
 
    private final Logger slf4jLogger = LoggerFactory.getLogger(Dashboard.class);
    
    private ComputerService workCompu;
    

    @Autowired(required = true)
    public void setComputerService(ComputerService ComputerService) {
        this.workCompu = ComputerService;
    }
    
    /**
     * The get version of the dashboard.
     */
    @RequestMapping(value="dashboard", method = RequestMethod.GET)
    public ModelAndView dashboardView(ModelMap model, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard");
        
        int page = 0;
        int size = 15;
    

        /* Numéro de page */
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                page--;
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for page " + e.getMessage());
                //response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return modelAndView;
            }
        }

        /* Taille de la page */
        if (request.getParameter("size") != null) {
            try {
                size = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for size " + e.getMessage());
                //response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return modelAndView;
            }
        }
        /* Récupération de l'ordre de tri */
        OrderType orderBy = null;
        if (request.getParameter("orderby") != null) {
            orderBy = OrderType.fromString(request.getParameter("orderby"));
            if (orderBy == null) {
                slf4jLogger.info("Bad parameter for orderby");
                //response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return modelAndView;
            }
        }

        /* Recherche par nom (computer | company) */
        String search = null;
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }


        /* Paramétrage de la requête d'ensemble */
        long computersLong = workCompu.getSizeTable();
        if (computersLong < (page - 1) * 15) {
            page = 1;
        }
        int low = (page * size) - size;

        /* Récupération des résultats pour la page|taille courante */
        List<Computer> computers = null;

        /* Tri de la liste si la recherche existe */
        
        if (search != null) {
            List<Computer> temp = workCompu.searchFor(search);
            computers = temp;
            /*if (temp.size() < size) {
                computers = temp;
            } else {
                computers = temp.subList(0, size);
            }*/
            computersLong = temp.size();
        } else if (orderBy != null) {
            computers = workCompu.getSetComputer(page, size, orderBy);
        } else {
            computers = workCompu.getSetComputer(page, size);
        }
        /*
        computers = new ArrayList<Computer>();
        Computer c = new Computer();
        c.setId(0);
        c.setName("Test");
        computers.add(c);*/
        
        /* Attributs de retour suite aux requêtes */
        model.addAttribute("computers", computers);
        model.addAttribute("nbComputers", computersLong);
        model.addAttribute("currentURL", request.getRequestURL());

        if (request.getQueryString() != null) {
            String urlParam = request.getQueryString();
            if (orderBy != null) {
                String result[] = urlParam.split("orderby=" + orderBy);
                urlParam = "";
                for (String s : result) {
                    urlParam += s;
                }
                model.addAttribute("currentParams", "?" + urlParam);
            } else {
                model.addAttribute("currentParams", "?" + urlParam + "&");
            }
        } else {
            model.addAttribute("currentParams", "?");

        }
        return modelAndView;
    }
}
