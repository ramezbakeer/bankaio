package com.bankaio.Bankaio.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Account")
public record Account(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id, Double balance, String Type, String currency, Date createdAt, String Status) {
}
