package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Transaction;
import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Repository.TransactionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class TransactionService implements TransactionServiceInt {

    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper  =  new ModelMapper();

    @Autowired
    TransactionService(TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }


    @Override
    public TransactionDTO createTransaction(String accountId, Double amount, String transType) {
        return null;
    }

    @Override
    public Boolean processTransaction(String transId) {
        return null;
    }

    @Override
    public String getTransactionStatus(String transId) {
        return "";
    }

    public List<TransactionDTO> getTransactionHistory(String accountId){
        List<Transaction> transactionEntities = transactionRepo.findAll();
        return transactionEntities
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }


}
