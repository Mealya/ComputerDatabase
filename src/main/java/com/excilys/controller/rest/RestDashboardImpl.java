package com.excilys.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.controller.Dashboard;
import com.excilys.controller.rest.RestDashboard;
import com.excilys.dto.rest.DashboardRestDTO;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.utils.OrderType;

@RestController
@RequestMapping("/rest/")
public class RestDashboardImpl implements RestDashboard {
    
private final Logger slf4jLogger = LoggerFactory.getLogger(Dashboard.class);
    
    private ComputerService workCompu;
    

    @Autowired(required = true)
    public void setComputerService(ComputerService computerService) {
        this.workCompu = computerService;
    }
    
    @RequestMapping(value={"/", "dashboard"}, method = RequestMethod.GET)
    public ResponseEntity<?> dashboardView(HttpServletRequest request) {
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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

        /* Taille de la page */
        if (request.getParameter("size") != null) {
            try {
                size = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for size " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        /* Récupération de l'ordre de tri */
        OrderType orderBy = null;
        if (request.getParameter("orderby") != null) {
            orderBy = OrderType.fromString(request.getParameter("orderby"));
            if (orderBy == null) {
                slf4jLogger.info("Bad parameter for orderby");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad order type");
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

        /* Récupération des résultats pour la page|taille courante */
        List<Computer> computers = null;

        /* Tri de la liste si la recherche existe */
        if (search != null) {
            List<Computer> temp = workCompu.searchFor(search);
            computers = temp;
            computersLong = temp.size();
        } else if (orderBy != null) {
            computers = workCompu.getSetComputer(page, size, orderBy);
        } else {
            computers = workCompu.getSetComputer(page, size);
        }
        
        DashboardRestDTO datas = new DashboardRestDTO();
        
        /* Attributs de retour suite aux requêtes */
        datas.setComputers(computers);
        datas.setNbComputers(computersLong);
        datas.setCurrent_url(request.getRequestURL().toString());

        if (request.getQueryString() != null) {
            String urlParam = request.getQueryString();
            if (orderBy != null) {
                String result[] = urlParam.split("orderby=" + orderBy);
                urlParam = "";
                for (String s : result) {
                    urlParam += s;
                }
                datas.setCurrent_url("?" + urlParam);
            } else {
                datas.setCurrent_url("?" + urlParam + "&");
            }
        } else {
            datas.setCurrent_url("?");

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(datas);
    }
}
