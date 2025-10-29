package com.example.demo.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminSigninForm;
import com.example.demo.form.AdminSignupForm;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    public static final String ADMIN_SESSION_KEY = "ADMIN_LOGIN";

    private final AdminService adminService;  

    
    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("form", new AdminSignupForm());
        return "admin/signup";
    }

    
    @PostMapping("/signup")
    public String doSignup(@Valid @ModelAttribute("form") AdminSignupForm form,
                           BindingResult bindingResult) {
        
        if (!form.getPassword().equals(form.getPasswordConfirm())) { 
            bindingResult.rejectValue("passwordConfirm", "mismatch", "パスワード（確認）が一致しません。");
        }
        if (bindingResult.hasErrors()) {
            return "admin/signup";
        }
        try {
            adminService.register(form.getLastName(), form.getFirstName(), form.getEmail(), form.getPassword());
        } catch (IllegalArgumentException e) { 
            bindingResult.rejectValue("email", "duplicated", e.getMessage());
            return "admin/signup";
        }
        return "redirect:/admin/signin";
    }

    
    @GetMapping("/signin")
    public String showSignin(Model model) {
        model.addAttribute("form", new AdminSigninForm());
        return "admin/signin";
    }

    
    @PostMapping("/signin")
    public String doSignin(@Valid @ModelAttribute("form") AdminSigninForm form,
                           BindingResult bindingResult,
                           HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "admin/signin";
        }
        Optional<Admin> adminOpt = adminService.authenticate(form.getEmail(), form.getPassword());
        if (adminOpt.isEmpty()) {
            bindingResult.reject("authFailed", "メールアドレスまたはパスワードが正しくありません。");
            return "admin/signin";
        }
        Admin admin = adminOpt.get();
        adminService.updateSignInTime(admin);
        session.setAttribute(ADMIN_SESSION_KEY, admin.getId());
        return "redirect:/admin/contacts";
    }

    
    @PostMapping("/signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/signin";
    }
}

