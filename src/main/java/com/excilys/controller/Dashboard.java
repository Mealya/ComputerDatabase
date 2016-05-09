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
import com.excilys.utils.OrderType;

public class Dashboard extends HttpServlet {
 
    private final Logger slf4jLogger = LoggerFactory.getLogger(Dashboard.class);
    private static final long serialVersionUID = 1L;

    /**
     * The get version of the dashboard.
     * 
     * @param request
     *            The HttpServletRequest
     * @param response
     *            The HttpServletResponse
     * @throws ServletException
     *             Error with servlet
     * @throws IOException
     *             Error with stream
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long debut = System.currentTimeMillis();

        int page = 1;
        int size = 15;

        /* Numéro de page */
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for page " + e.getMessage());
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        /* Taille de la page */
        if (request.getParameter("size") != null) {
            try {
                size = Integer.parseInt(request.getParameter("size"));
            } catch (NumberFormatException e) {
                slf4jLogger.info("Bad parameter for size " + e.getMessage());
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        /* Récupération de l'ordre de tri */
        OrderType orderBy = null;
        if (request.getParameter("orderby") != null) {
            orderBy = OrderType.fromString(request.getParameter("orderby"));
            if (orderBy == null) {
                slf4jLogger.info("Bad parameter for orderby");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        /* Recherche par nom (computer | company) */
        String search = null;
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        HeavyComputerDAO workingDB = new HeavyComputerDAO();

        /* Paramétrage de la requête d'ensemble */
        long computersLong = workingDB.getSizeTable();
        if (computersLong < (page - 1) * 15) {
            slf4jLogger.info("Error making page, redirect...");
            page = 1;
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            //return;
        }
        int low = (page * size) - size;

        /* Récupération des résultats pour la page|taille courante */
        List<Computer> computers = null;

        /* Tri de la liste si la recherche existe */
        if (search != null) {
            /*
            for (int i = 0; i < computers.size(); i++) {
                if (!computers.get(i).getName().equals(search)) {
                    if (computers.get(i).getComp() != null) {
                        if (!computers.get(i).getComp().getName()
                                .equals(search)) {
                            computers.remove(i);
                            i--;
                        }
                    } else {
                        computers.remove(i);
                        i--;
                    }
                }
            }*/
            List<Computer> temp = workingDB.searchFor(search);
            if (temp.size() < size) {
                computers = temp;
            } else {
                computers = temp.subList(0, size);
            }
           
        } else if (orderBy != null) {
            computers = workingDB.getSetComputer(low, size, orderBy);
        } else {
            computers = workingDB.getSetComputer(low, size);
        }
        
        
        /* Attributs de retour suite aux requêtes */
        request.setAttribute("computers", computers);
        request.setAttribute("nbComputers", computersLong);
        request.setAttribute("currentURL", request.getRequestURL());

        if (request.getQueryString() != null) {
            String urlParam = request.getQueryString();
            if (orderBy != null) {
                String result[] = urlParam.split("orderby=" + orderBy);
                urlParam = "";
                for (String s : result) {
                    urlParam += s;
                }
                request.setAttribute("currentParams", "?" + urlParam);
            } else {
                request.setAttribute("currentParams", "?" + urlParam + "&");
            }
        } else {
            request.setAttribute("currentParams", "?");

        }
        slf4jLogger.debug("Exécution Dashboard : " + (System.currentTimeMillis()-debut));
        
        this.getServletContext()
                .getRequestDispatcher("/vues/raw/views/dashboard.jsp")
                .forward(request, response);
    }
}
