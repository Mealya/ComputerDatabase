package com.excilys.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.controller.EditComputer;
import com.excilys.controller.dtomapper.Mappator;
import com.excilys.controller.rest.RestDeleteComputer;
import com.excilys.dto.EditComputerDTO;
import com.excilys.dto.rest.EditComputerRestDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping("/rest/")
public class RestEditComputerImp implements RestDeleteComputer {
    private final Logger slf4jLogger = LoggerFactory
            .getLogger(EditComputer.class);

    @Autowired
    private CompanyService workCompt;
    
    @Autowired
    private ComputerService workCompu;
    

    @RequestMapping(value="editComputerForm", method = RequestMethod.GET)
    public ResponseEntity<?> editComputerView(HttpServletRequest request) {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            slf4jLogger.info("Bad parameter for id : " + request.getParameter("id"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad parameter id");
        }
        if (id <= 0) {
            slf4jLogger.info("Request with id : " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is invalid");
        }
        EditComputerRestDTO datas = new EditComputerRestDTO();
        
        datas.setId("" + id);


        List<Company> companies = workCompt.getCompanies();
        datas.setCompanies(companies);
        Computer temp = workCompu.getComputer((long) id);
        if (temp.getName() != null) {
            datas.setComputerName(temp.getName());
        }
        if (temp.getIntro() != null) {
            datas.setIntroduced(temp.getIntro().toString());
        }
        if (temp.getDisco() != null) {
            datas.setDiscontinued(temp.getDisco().toString());
        }
        if (temp.getComp() != null) {
            datas.setCompanyId("" + temp.getComp().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(datas);
    }


    @RequestMapping(value="editComputer", method = RequestMethod.POST)
    public ResponseEntity<?> editComputer(HttpServletRequest request) {

        Computer computer = Mappator.computerToEdit(request.getParameter("id"),
                                                    request.getParameter("computerName"),
                                                    request.getParameter("introduced"),
                                                    request.getParameter("discontinued"),
                                                    request.getParameter("companyId"));
        
        if (computer != null) {
            workCompu.updateComputer(computer);
        } else {
            slf4jLogger.warn("Fail to edit a computer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to edit computer");
        }
    
        return ResponseEntity.status(HttpStatus.OK).body(computer);
    }
}
