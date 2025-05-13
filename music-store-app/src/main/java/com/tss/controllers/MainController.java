package com.tss.controllers;

import org.springframework.stereotype.Controller;
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
}