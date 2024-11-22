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

    public TransactionService(TransactionRepo transactionRepo,AccountServiceInt accountService){
        this.transactionRepo = transactionRepo;
        this.accountService=accountService;
    }


    @Override
    public TransactionDTO createTransaction(Long userId,Long accountId, Double amount,TransactionType transType, Long referenceId) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountDto(accountService.getAccountDetails(userId,accountId));
        transactionDTO.setAmount(amount);
        transactionDTO.setTransactionType(transType);
        transactionDTO.setTransactionStatus(TransactionStatus.PENDING);
        transactionDTO.setReferenceId(referenceId);
      try{
          switch (transType){
              case TRANSFER -> accountService.transferFunds(userId,accountId,referenceId,amount);
              case DEPOSIT -> accountService.deposit(accountId,amount);
              case WITHDRAWAL, BILL_PAYMENT,LOAN_PAYMENT -> accountService.withdraw(accountId,amount);
              default -> throw new IllegalArgumentException("Invalid transaction type");
          }
          transactionDTO.setTransactionStatus(TransactionStatus.COMPLETED);
      }catch (RuntimeException e){
          transactionDTO.setTransactionStatus(TransactionStatus.FAILED);
          transactionRepo.save(modelMapper.map(transactionDTO,Transaction.class));
          throw e;
      }


        return modelMapper.map(transactionRepo.save(modelMapper.map(transactionDTO,Transaction.class)),TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> getTransactionHistory(Long userId,Long accountId){
        List<Transaction> transactionEntities = transactionRepo.findAllByUser_UserIdAndAccount_AccountId(userId,accountId);
        return transactionEntities
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO viewTransactionDetails(Long userId, Long accountId, Long transactionId) {
        Transaction transaction = transactionRepo.findByUser_UserIdAndAccount_AccountIdAndTransactionId(userId,accountId,transactionId).orElseThrow(()->new RuntimeException("NO SUCH TRANSACTION"));
        return modelMapper.map(transaction,TransactionDTO.class);
    }


}
