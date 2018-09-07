package com.demo.spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @RequestMapping(path = "/welcome")
    public String home(HttpServletRequest request) {
        return "Welcome";
    }

    @RequestMapping(path = "/user/account")
    public String hello(HttpServletRequest request) {
        return "User Account is accessible";
    }

    @RequestMapping(path = "/admin/account")
    public String welcome(HttpServletRequest request) {
        return "Admin account is accessible";
    }

    @RequestMapping(path = "/getaccount")
    public String getaccount(HttpServletRequest request) {

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        b.encode("welcome1");
        String encodedCred = "user:" + b.encode("welcome1");

        HttpHeaders header = new HttpHeaders();
        header.set("Accept", "application/json");
        header.set("Authorization", "Basic " + encodedCred);

        HttpEntity req = new HttpEntity(header);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/user/account", HttpMethod.GET,
                req, String.class);

        return response.getBody();
    }

}
