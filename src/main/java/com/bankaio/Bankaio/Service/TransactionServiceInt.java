package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.TransactionDTO;

import java.util.List;

public interface TransactionServiceInt {
    TransactionDTO createTransaction(Long userId,Long accountId, Double amount, TransactionType transType, Long referenceId);
    List<TransactionDTO> getTransactionHistory(Long userId,Long accountId);
    TransactionDTO viewTransactionDetails(Long userId, Long accountId,Long transactionId);
}
