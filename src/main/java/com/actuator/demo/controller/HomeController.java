package com.actuator.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/ip")
    public String ip(HttpServletRequest request) {
       String ip = request.getRemoteAddr();
       return ip;
    }
}
