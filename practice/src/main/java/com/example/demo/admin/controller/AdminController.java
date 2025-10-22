package com.example.demo.admin.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.service.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/contacts")
@RequiredArgsConstructor
public class AdminController {

   
    private final ContactService contactService;

    @GetMapping
    public String contactList(Model model) {
        model.addAttribute("contacts", contactService.findAllOrderByUpdatedAtDesc());
        return "admin/contactList"; 
    }
    
    @GetMapping("/{id}")
    public String contactDetail(@PathVariable Long id, Model model) {
    	model.addAttribute("contact", contactService.findById(id));
    	return "admin/contactDetail";
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
    	model.addAttribute("form", contactService.findById(id));
    	model.addAttribute("contactId", id);
    	return "admin/contactEdit";
    }
    
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ContactForm());
        return "admin/contactNew";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("form") @Valid ContactForm form, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		return "admin/contactNew";
    	}
        contactService.saveContact(form);
        return "redirect:/admin/contacts";
    }

    
    @PostMapping("/{id}/edit")
    public String updateContact(@PathVariable Long id, @ModelAttribute("form") @Valid ContactForm form, 
    		BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contactId", id);
            return "admin/contactEdit";
        }
        contactService.update(id, form);
        return "redirect:/admin/contacts/" + id;
    }
    
    @PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.delete(id);
        return "redirect:/admin/contacts";
    }
}

