package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.Transaction;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long billId;
    private Double amount;
    private Date dueDate;
    private BillStatus status;
    private String billDescription;
    private UserDto userDto;
    private TransactionDTO transactionDTO;
}
