package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Model.UserDto;

public interface UserServiceInt {
    public void login( Long userId , String password );
    public UserDto viewProfile(Long userId );
    public void updateProfile(Long userId, UserDto updatedDetails);
    public void deleteUser(Long userId);
    public TransactionDTO makeTransaction(Long accountId, Double amount ,String transactionType);
    public TransactionDTO payBill(Long billId, Double amount );
}
