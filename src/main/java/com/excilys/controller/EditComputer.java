package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.controller.validator.Validator;
import com.excilys.dto.EditComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class EditComputer {

    private final Logger slf4jLogger = LoggerFactory
            .getLogger(EditComputer.class);

    /**
     * The get version of edit a computer.
     */
    @RequestMapping(value="editComputerForm", method = RequestMethod.GET)
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

        return "editComputer";
    }

    /**
     * The post version of edit a computer.
     */
    @RequestMapping(value="editComputer", method = RequestMethod.POST)
    public ModelAndView editComputer(HttpServletRequest request, @Valid EditComputerDTO editcomputerdto, BindingResult bindingResult)
            throws IOException {
        
        //TODO : when no ID in form
        if (bindingResult.hasErrors()) {
            /*for(ObjectError e : bindingResult.getAllErrors()){
                System.out.println(e);
            }*/
            return new ModelAndView("redirect:/editComputerForm?id="+editcomputerdto.getId());
        }
        
        Computer computer = Validator.validateComputerEdit(editcomputerdto.getId(), editcomputerdto.getComputerName(), editcomputerdto.getIntroduced(), editcomputerdto.getDiscontinued(), editcomputerdto.getCompanyId());
         
        if (computer != null) {
            ComputerService serv = new ComputerService();
            serv.updateComputer(computer);
        } else {
            slf4jLogger.warn("Fail to edit a computer");
            return new ModelAndView("redirect:/editComputerForm");
        }
    
        return new ModelAndView("redirect:/dashboard?retourn=1");
    }
}
