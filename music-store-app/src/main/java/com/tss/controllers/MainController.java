package com.tss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index"; 
    }
         
    @GetMapping("/albums")
    public String albums() {
        return "albums";
    }
    
    @GetMapping("/users")
    public String users() {
        return "users";
    }
    
    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }
      
    @Autowired
    private Environment env;
    
    @Autowired
    private BuildProperties buildProperties;

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
        
        String artifactApp = buildProperties.getArtifact();
        String versionApp = buildProperties.getVersion();
        String springVersion = SpringVersion.getVersion();
        model.addAttribute("artifactName",artifactName);
        model.addAttribute("buildVersion",buildVersion);
        model.addAttribute("buildTimestamp",buildTimestamp);
        model.addAttribute("jdkVersion",jdkVersion);
        model.addAttribute("springBootVersion",springBootVersion);
        model.addAttribute("springVersion",springVersion);
        return "appInfo"; // Thymeleaf widok info.html
    }
}