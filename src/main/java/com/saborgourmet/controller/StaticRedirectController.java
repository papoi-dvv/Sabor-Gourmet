package com.saborgourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticRedirectController {

    /**
     * Compatibilidad con enlaces antiguos que apuntan a /admin.html.
     * Redirige al controlador que sirve la plantilla administradora (/admin -> /admin/dashboard).
     */
    @GetMapping("/admin.html")
    public String redirectAdminHtml() {
        return "redirect:/admin";
    }

}
