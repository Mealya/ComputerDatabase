package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorController {

    @RequestMapping(value="/errors/400.jsp")
    public String handle400() {
        return "400";
    }
    
    @RequestMapping(value="/errors/403.jsp")
    public String handle403() {
        return "403";
    }
    
    @RequestMapping(value="/errors/404.jsp")
    public String handle404() {
        return "404";
    }

    @RequestMapping(value="/errors/500.jsp")
    public String handle500() {
        return "500";
    }
    

}
