package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.AccountDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface AccountServiceInt {
     void createAccount(UserDto userDto, AccountDto accountDto);
     AccountDto getAccountDetails(Long userId,Long accountId);
     List<AccountDto> getAllAccounts(Long userId);
     void closeAccount(Long userId,Long accountId);
     void deposit(Long accountId, Double amount);
     void withdraw(Long accountId, Double amount);
     void transferFunds(Long userId,Long fromAccountId, Long toAccountId, Double amount);
}
