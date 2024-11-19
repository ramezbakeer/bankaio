package com.bankaio.Bankaio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="account")
public class Account
 {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
     private Double balance;
     private String Type;
     private String currency;
     @CreationTimestamp
     @Column(updatable = false)
     private LocalDateTime createdAt;
     private String Status;
     @ManyToOne
     @JoinColumn(name="userId",nullable = false)
     private User user;
     @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private List<Transaction> transactionList;
}
