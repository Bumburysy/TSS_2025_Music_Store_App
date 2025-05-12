package com.tss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index"; 
    }
    
    // Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
