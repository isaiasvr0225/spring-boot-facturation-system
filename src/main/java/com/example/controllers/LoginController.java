package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes flashMessage){

        if (principal != null){
            flashMessage.addFlashAttribute("info", "You are already signed");
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Clave o contraseña incorrecta¡");
        }

        return "login";
    }
}
