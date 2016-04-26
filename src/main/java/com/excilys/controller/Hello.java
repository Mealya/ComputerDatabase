package com.excilys.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(value = "/hello", name = "hello-servlet")

public class Hello extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Ceci est une page générée depuis une servlet.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
