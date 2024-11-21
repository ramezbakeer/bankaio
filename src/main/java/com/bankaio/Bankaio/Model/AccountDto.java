package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.Transaction;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Double balance;
    private String Type;
    private LocalDateTime createdAt;
    private AccountStatus Status;
    private User user;
    private List<Transaction> transactionList;
}
