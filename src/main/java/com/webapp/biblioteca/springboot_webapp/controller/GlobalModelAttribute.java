package com.webapp.biblioteca.springboot_webapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.webapp.biblioteca.springboot_webapp.service.CustomUserDetails;

import org.springframework.ui.Model;


@ControllerAdvice
public class GlobalModelAttribute {

    @ModelAttribute
    public void addUserInfo(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioActual", userDetails.getUsuario());
        }
    }
}