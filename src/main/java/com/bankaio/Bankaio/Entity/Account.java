package com.bankaio.Bankaio.Entity;

import com.bankaio.Bankaio.Entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="account")
public class Account
 {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long accountId;
     private Double balance;
     private String Type;
     private String currency;
     @CreationTimestamp
     @Column(updatable = false)
     private LocalDateTime createdAt;
     @Enumerated(EnumType.STRING)
     private AccountStatus Status;
     @ManyToOne
     @JoinColumn(name="user_id",nullable = false)
     private User user;
     @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private List<Transaction> transactionList;
}
