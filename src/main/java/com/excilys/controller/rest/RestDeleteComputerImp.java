package com.excilys.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.controller.rest.RestDeleteComputer;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping("/rest/")
public class RestDeleteComputerImp implements RestDeleteComputer {

    @Autowired
    private ComputerService workCompu;
    
    /**
     * The post version of delete a computer.
     */
    @RequestMapping(value="deleteComputer", method = RequestMethod.POST)
    public ResponseEntity<?> deleteComputer(HttpServletRequest request) throws IOException {
        String params = request.getParameterValues("selection")[0]; 
     
        String result[] = (params.split(",")); 

        for (String c : result) {
            try {
                workCompu.deleteComputer(Long.parseLong(c));
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossible to delete");
            }
        }
       
        return ResponseEntity.status(HttpStatus.OK).body("Computer(s) deleted !");
    }
}
