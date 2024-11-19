package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private Long phoneNumber;
    private String address;
    private String role;// "Customer" or "Admin"
    private String password;
    private LocalDateTime createdAt;
    private Date lastLogin;
    private List<AccountDto> accountList;
    private List<TransactionDTO> transactions;
    private List<BillDto> bills;
    private List<NotificationDto> notificationList;
    private List<LoanDto> loanList;
}

