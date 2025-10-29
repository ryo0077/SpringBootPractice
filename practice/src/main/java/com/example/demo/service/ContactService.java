package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
    void saveContact(ContactForm contactForm);
    void update(Long id, ContactForm form);
    void delete(Long id);
    List<Contact> findAll();
    List<Contact> findAllOrderByUpdatedAtDesc();
    Contact findById(Long id);
}
