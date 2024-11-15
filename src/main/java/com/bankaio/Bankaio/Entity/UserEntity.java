package com.bankaio.Bankaio.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="User")
public record UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long userId , String name, String email, Long phoneNumber,
        String address, String role, // "Customer" or "Admin"
        String password, Date createdAt, Date lastLogin) {  }
