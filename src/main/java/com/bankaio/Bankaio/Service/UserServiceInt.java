package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Model.UserDto;

public interface UserServiceInt {
    public void createUser(UserDto userDto);
    public UserDto viewProfile(Long userId );
    public void updateProfile(Long userId, UserDto updatedDetails);
    public void deleteUser(Long userId);
    public TransactionDTO makeTransaction(Long accountId, Double amount , TransactionType transactionType);
    public TransactionDTO makeTransaction(Long fromAccountId, Long toAccountId, Double amount);
    public TransactionDTO payBill(Long billId,Long accountId, Double amount );
    public TransactionDTO makeLoanPayment(Long loanId,Long accountId,Double paymentAmount);
    public void requestLoan(Long userId, Double amount, int tenureMonths);
    public void processLoan(Long loanId, Double interestRate);
}
