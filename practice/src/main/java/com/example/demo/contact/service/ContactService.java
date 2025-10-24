package com.example.demo.contact.service;

import java.util.List;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

public interface ContactService {
    void saveContact(ContactForm contactForm);
    void update(Long id, ContactForm form);
    void delete(Long id);
    List<Contact> findAll();
    List<Contact> findAllOrderByUpdatedAtDesc();
    Contact findById(Long id);
}
