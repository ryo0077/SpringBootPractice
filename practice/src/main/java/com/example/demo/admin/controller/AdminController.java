package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.contact.service.ContactService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public String contactList(Model model) {
        model.addAttribute("contacts", contactService.findAllOrderByUpdatedAtDesc());
        return "admin/contactList"; 
    }
}

