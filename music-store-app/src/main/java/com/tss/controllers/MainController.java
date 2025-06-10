package com.tss.controllers;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(HttpSession session, Model model, Principal principal) {
        if (principal != null) {

            if (session.getAttribute("loginTime") == null) {
                session.setAttribute("loginTime", LocalDateTime.now());
            }

            LocalDateTime loginTime = (LocalDateTime) session.getAttribute("loginTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String formattedLoginTime = loginTime.format(formatter);
            model.addAttribute("formattedLoginTime", formattedLoginTime);

            Integer counter = (Integer) session.getAttribute("visitCount");
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            session.setAttribute("visitCount", counter);
            model.addAttribute("visitCount", counter);

            Duration sessionDuration = Duration.between(loginTime, LocalDateTime.now());
            model.addAttribute("sessionDuration", sessionDuration.toMinutes());
            model.addAttribute("loginTime", loginTime);
        }

        return "index";
    }
       
    @GetMapping("/albums")
    public String albums() {
        return "albums";
    }
    
    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }
      
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @Autowired
    private Environment env;
    
    @Value("${artifact.name}")
    private String artifactName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @Value("${jdkversion}")
    private String jdkVersion;

    @Value("${springbootversion}")
    private String springBootVersion;

    @GetMapping("/appInfo")
    public String appInfo(Model model) {
        model.addAttribute("appName", env.getProperty("info.app.name"));
        model.addAttribute("appVersion", env.getProperty("info.app.version"));
        model.addAttribute("appDescription", env.getProperty("info.app.description"));
        model.addAttribute("contactEmail", env.getProperty("info.contact.email"));
        model.addAttribute("contactPhone", env.getProperty("info.contact.phone"));
        model.addAttribute("contactTeam", env.getProperty("info.contact.team"));
        
        String springVersion = SpringVersion.getVersion();
        model.addAttribute("artifactName",artifactName);
        model.addAttribute("buildVersion",buildVersion);
        model.addAttribute("buildTimestamp",buildTimestamp);
        model.addAttribute("jdkVersion",jdkVersion);
        model.addAttribute("springBootVersion",springBootVersion);
        model.addAttribute("springVersion",springVersion);
        return "appInfo";
    }
}