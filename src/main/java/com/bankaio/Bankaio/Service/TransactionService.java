package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.TransactionEntity;
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

    @Autowired
    TransactionService(TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }


    public List<TransactionDTO> getTransactionHistory(String accountId){
        List<TransactionEntity> transactionEntities = transactionRepo.findAll();
        return transactionEntities
                .stream()
                .map(transactionEntity -> modelMapper.map(transactionEntity, TransactionDTO.class))
                .collect(Collectors.toList());
    }


}
