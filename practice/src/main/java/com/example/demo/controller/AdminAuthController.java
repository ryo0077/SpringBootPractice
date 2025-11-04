package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminSignupForm;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminService adminService; 

    
    @GetMapping("/signin")
    public String signinPage() {
        return "admin/signin";    
    }

    
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("form", new AdminSignupForm());
        return "admin/signup";
    }

    
    @PostMapping("/signup")
    public String doSignup(@ModelAttribute("form") @Valid AdminSignupForm form,
                           BindingResult bindingResult,          
                           Model model) {
       

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "mismatch", "パスワード（確認）が一致しません。");
        }

        if (bindingResult.hasErrors()) {
            return "admin/signup";
        }

        try {
            adminService.register(
                form.getLastName(),
                form.getFirstName(),
                form.getEmail(),
                form.getPassword()
            );
        } catch (IllegalArgumentException e) { 
            bindingResult.rejectValue("email", "duplicated", e.getMessage());
            return "admin/signup";
        }

       
        return "redirect:/admin/signin?registered";
    }
}
