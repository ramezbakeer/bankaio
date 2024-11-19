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
@Table(name="user")
public class User{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;
        private String name;
        private String email;
        private Long phoneNumber;
        private String address;
        private String role;// "Customer" or "Admin"
        private String password;
        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime createdAt;
        private Date lastLogin;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Account> accountList;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Transaction> transactions;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Bill> bills;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Notification> notificationList;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Loan> loanList;
}
