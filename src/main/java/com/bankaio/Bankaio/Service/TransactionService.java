package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Transaction;
import com.bankaio.Bankaio.Entity.enums.TransactionStatus;
import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Repository.TransactionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements TransactionServiceInt {

    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper  =  new ModelMapper();
    private final AccountServiceInt accountService;

    @Autowired
    private TransactionService(TransactionRepo transactionRepo,AccountServiceInt accountService){
        this.transactionRepo = transactionRepo;
        this.accountService=accountService;
    }


    @Override
    public TransactionDTO createTransaction(Long userId,Long accountId, Double amount,TransactionType transType, Long referenceId) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountDto(accountService.getAccountDetails(userId,accountId));
        transactionDTO.setAmount(amount);
        transactionDTO.setTransType(transType);
        transactionDTO.setTransStatus(TransactionStatus.PENDING);
        transactionDTO.setReferenceId(referenceId);
      try{
          switch (transType){
              case TRANSFER -> accountService.transferFunds(userId,accountId,referenceId,amount);
              case DEPOSIT -> accountService.deposit(accountId,amount);
              case WITHDRAWAL, BILL_PAYMENT,LOAN_PAYMENT -> accountService.withdraw(accountId,amount);
              default -> throw new IllegalArgumentException("Invalid transaction type");
          }
          transactionDTO.setTransStatus(TransactionStatus.COMPLETED);
      }catch (RuntimeException e){
          transactionDTO.setTransStatus(TransactionStatus.FAILED);
          transactionRepo.save(modelMapper.map(transactionDTO,Transaction.class));
          throw e;
      }


        return modelMapper.map(transactionRepo.save(modelMapper.map(transactionDTO,Transaction.class)),TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> getTransactionHistory(Long userId,Long accountId){
        List<Transaction> transactionEntities = transactionRepo.findByUser_UserIdAndAccount_AccountId(userId,accountId);
        return transactionEntities
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }


}
