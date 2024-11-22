package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.AccountCreateDto;
import com.bankaio.Bankaio.Model.AccountDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface AccountServiceInt {
     AccountDto createAccount(UserDto userDto, AccountCreateDto accountDto);
     AccountDto getAccountDetails(Long userId,Long accountId);
     AccountDto getAccountDetailsById(Long accountId);
     List<AccountDto> getAllAccounts(Long userId);
     void closeAccount(Long userId,Long accountId);
     void deposit(Long accountId, Double amount);
     void withdraw(Long accountId, Double amount);
     void transferFunds(Long userId,Long fromAccountId, Long toAccountId, Double amount);
}
