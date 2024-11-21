package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.*;

import java.util.List;

public interface UserServiceInt {
    void createUser(UserDto userDto);
    UserDto viewProfile(Long userId );
     void updateProfile(Long userId, UserDto updatedDetails);
     void deleteUser(Long userId);
     TransactionDTO makeTransaction(Long userId, Long accountId, Double amount , TransactionType transactionType);
     TransactionDTO transferFund(Long userId, Long fromAccountId, Long toAccountId, Double amount);
     void addBill(Long userId, BillDto BillDto);
     TransactionDTO payBill(Long userId,Long billId,Long accountId, Double amount );
    TransactionDTO makeLoanPayment(Long userId,Long loanId,Long accountId,Double paymentAmount);
     void requestLoan(Long userId, Double amount, int tenureMonths);
     void processLoan(Long loanId, Double interestRate);
     void createAccount(Long userId, AccountDto accountDto);
     AccountDto getAccountDetails(Long userId, Long accountId);
     List<AccountDto> getAllAccounts(Long userId);
     void closeAccount(Long userId,Long accountId);
    List<TransactionDTO> getTransactionHistory(Long userId,Long accountId);
    List<LoanDto> viewAllLoans(Long userId);
    List<NotificationDto> viewNotifications(Long userId);
     void markAsReadNotification(Long notificationId);
    List<BillDto> viewAllBills(Long userId);
}
