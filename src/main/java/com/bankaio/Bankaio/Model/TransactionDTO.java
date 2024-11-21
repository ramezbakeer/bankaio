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
    private Long transId;
    private Double amount;
    private TransactionType transType;
    private TransactionStatus transStatus;
    private AccountDto accountDto;
    private UserDto userDto;
    private BillDto billDto;
    private Long referenceId;
}
