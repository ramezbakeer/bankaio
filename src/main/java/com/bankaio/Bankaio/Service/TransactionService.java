package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Transaction;
import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Repository.TransactionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class TransactionService implements TransactionServiceInt {

    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper  =  new ModelMapper();
    private final AccountService accountService;

    @Autowired
    private TransactionService(TransactionRepo transactionRepo,AccountService accountService){
        this.transactionRepo = transactionRepo;
        this.accountService=accountService;
    }


    @Override
    public TransactionDTO createTransaction(Long accountId, Double amount, TransactionType transType, Long referenceId) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountDto(accountService.getAccountDetails(accountId));
        transactionDTO.setAmount(amount);
        transactionDTO.setTransType(transType);
        transactionDTO.setTransStatus("PENDING");
        transactionDTO.setReferenceId(referenceId);
        switch (transType){
            case TRANSFER -> accountService.transferFunds(accountId,referenceId,amount);
            case DEPOSIT -> accountService.deposit(accountId,amount);
            case WITHDRAWAL, BILL_PAYMENT,LOAN_PAYMENT -> accountService.withdraw(accountId,amount);
        }

        transactionDTO.setTransStatus("COMPLETED");

        return modelMapper.map(transactionRepo.save(modelMapper.map(transactionDTO,Transaction.class)),TransactionDTO.class);
    }

    @Override
    public String getTransactionStatus(Long transId) {
        return transactionRepo.findById(transId).orElseThrow(RuntimeException::new).getTransactionStatus();
    }

    public List<TransactionDTO> getTransactionHistory(Long accountId){
        List<Transaction> transactionEntities = transactionRepo.findAll();
        return transactionEntities
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }


}
