package com.excilys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.service.ComputerService;


@Controller
public class DeleteComputer {

    @Autowired
    private ComputerService workCompu;
    
    /**
     * The post version of delete a computer.
     */
    @RequestMapping(value="deleteComputer", method = RequestMethod.POST)
    public ModelAndView deleteComputer(HttpServletRequest request) throws IOException {
        String params = request.getParameterValues("selection")[0]; 
     
        String result[] = (params.split(",")); 

        for (String c : result) {
            try {
                workCompu.deleteComputer(Long.parseLong(c));
            } catch (NumberFormatException e) {
                //response.sendRedirect("/ComputerDatabaseMaven/dash");
                return new ModelAndView("redirect:/dashboard");
            }
        }
        
        //response.sendRedirect("/ComputerDatabaseMaven/dash?retourn=2");
        //request.setAttribute("retourn", 2);
        return new ModelAndView("redirect:/dashboard?retourn=2");
    }
}
