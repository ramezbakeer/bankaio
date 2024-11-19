package com.bankaio.Bankaio.Entity;

import com.bankaio.Bankaio.Entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionStatus;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_At;
    @ManyToOne
    @JoinColumn(name="id")
    private Account account;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @OneToOne
    @JoinColumn(name = "billId")
    private Bill bill;
    private Long referenceId;
}
