package com.demo.spring.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String getaccount(HttpServletRequest request) throws UnsupportedEncodingException {

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

    @RequestMapping(value = "/logmeout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
