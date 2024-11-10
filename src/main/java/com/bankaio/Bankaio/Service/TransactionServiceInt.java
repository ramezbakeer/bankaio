package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.TransactionDTO;

import java.util.List;

public interface TransactionServiceInt {
    public TransactionDTO createTransaction(String accountId, Double amount, String transType);
    public Boolean processTransaction(String transId);
    public String getTransactionStatus(String transId);
    public List<TransactionDTO> getTransactionHistory(String accountId);

}
