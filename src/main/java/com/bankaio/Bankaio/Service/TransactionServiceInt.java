package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.TransactionDTO;

import java.util.List;

public interface TransactionServiceInt {
    public TransactionDTO createTransaction(Long accountId, Double amount, TransactionType transType, Long referenceId);
    public String getTransactionStatus(Long transId);
    public List<TransactionDTO> getTransactionHistory(Long accountId);

}
