package com.demo.spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @RequestMapping(path = "/welcome", method = RequestMethod.GET)
    public String welcome(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(HttpServletRequest request, Model map) {

        map.addAttribute("employeeName", request.getParameter("empName"));
        return "index";
    }
}
