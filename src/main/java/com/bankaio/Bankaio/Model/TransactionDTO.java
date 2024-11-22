package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.Account;
import com.bankaio.Bankaio.Entity.Bill;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.TransactionStatus;
import com.bankaio.Bankaio.Entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    private Double amount;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private Long referenceId;
    private AccountDto accountDto;
    private UserDto userDto;
}
