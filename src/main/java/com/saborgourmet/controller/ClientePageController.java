package com.saborgourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientePageController {
    
    @GetMapping("/clientes")
    public String clientes(Model model) {
        return "clientes";
    }
}