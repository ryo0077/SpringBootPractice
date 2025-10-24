package com.example.demo.contact.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
@Entity
@Data
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "contact_type", nullable = false)
    private String contactType;

    @Column(name = "body", nullable = false)
    private String body;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
}