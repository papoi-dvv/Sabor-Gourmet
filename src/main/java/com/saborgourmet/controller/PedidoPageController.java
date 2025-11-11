package com.saborgourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoPageController {
    
    @GetMapping("/pedidos/cocina")
    public String cocina(Model model) {
        model.addAttribute("pedidosPendientes", java.util.Collections.emptyList());
        model.addAttribute("pedidosEnPreparacion", java.util.Collections.emptyList());
        return "panel-cocina";
    }
}