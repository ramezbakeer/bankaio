package com.bankaio.Bankaio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Double amount;

    private String transactionType;
    private String transactionStatus;
    @ManyToOne
    @JoinColumn(name="id")
    private Account account;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @OneToOne
    @JoinColumn(name = "billId")
    private Bill bill;
}
