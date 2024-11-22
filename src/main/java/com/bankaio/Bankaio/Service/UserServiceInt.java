package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.*;

import java.util.List;

public interface UserServiceInt {
    UserDto createUser(UserRequestDto userRequestDto);
    UserDto viewProfile(Long userId);
    void updateProfile(Long userId, UserUpdateDto updatedDetails);
    void deleteUser(Long userId);
    TransactionDTO transferFund(Long userId, Long fromAccountId, TransferDto transferDto);
    BillDto addBill(Long userId, BillCreateDto BillDto);
    TransactionDTO payBill(Long userId,Long accountId,Long billId);
    TransactionDTO makeLoanPayment(Long userId,Long accountId, TransferDto transferDto);
    LoanDto requestLoan(Long userId, LoanRequestDto loanRequestDto);
    void processLoan(Long userId,Long loanId, Double interestRate);
    AccountDto createAccount(Long userId, AccountCreateDto accountDto);
    AccountDto getAccountDetails(Long userId, Long accountId);
    List<AccountDto> getAllAccounts(Long userId);
    void closeAccount(Long userId,Long accountId);
    List<TransactionDTO> getTransactionHistory(Long userId,Long accountId);
    List<LoanDto> viewAllLoans(Long userId);
    List<NotificationDto> viewNotifications(Long userId);
    void markAsReadNotification(Long userId,Long notificationId);
    List<BillDto> viewAllBills(Long userId);
    BillDto viewBillDetails(Long userId,Long billId);
    LoanDto viewLoanDetails(Long userId,Long loanId);
    TransactionDTO viewTransactionDetails(Long userId, Long accountId,Long transactionId);
}
