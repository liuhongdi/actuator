package com.actuator.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/ip")
    public String ip(HttpServletRequest request) {
       String ip = request.getRemoteAddr();
       return ip;
    }

    //session详情
    @GetMapping("/session")
    @ResponseBody
    public String session() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Enumeration e   =   session.getAttributeNames();
        String s = "";
        while( e.hasMoreElements())   {
            String sessionName=(String)e.nextElement();
            s += "name="+sessionName+";<br/>";
            s += "value="+session.getAttribute(sessionName)+";";
        }
        return s;
    }
}
