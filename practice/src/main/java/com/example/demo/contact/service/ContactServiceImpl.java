package com.example.demo.contact.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    	private final ContactRepository contactRepository;
    @Transactional(readOnly = true)
    @Override
         public List<Contact> findAll() {
    	        return contactRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllOrderByUpdatedAtDesc() {
        return contactRepository.findAllByOrderByUpdatedAtDesc();
    }
    

    
    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "該当するIDがありません"));
    }
    
    @Transactional
    @Override
    public void saveContact(ContactForm form) {
    	Contact c =new Contact();
    	applyForm(c, form);
    	contactRepository.save(c);
    }
    
    @Transactional
    @Override
    public void update(Long id, ContactForm form) {
        Contact c = contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "該当するIDがありません"));
        applyForm(c, form);
        contactRepository.save(c);
    }
    
    @Transactional
    @Override
    public void delete(Long id) {
    	contactRepository.deleteById(id);
    }
    
    private void applyForm(Contact c, ContactForm f) {
        c.setLastName(f.getLastName());
        c.setFirstName(f.getFirstName());
        c.setEmail(f.getEmail());
        c.setPhone(f.getPhone());
        c.setZipCode(f.getZipCode());
        c.setAddress(f.getAddress());
        c.setBuildingName(f.getBuildingName());
        c.setContactType(f.getContactType());
        c.setBody(f.getBody());
    }
}

