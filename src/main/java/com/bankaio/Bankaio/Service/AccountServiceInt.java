package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.AccountDto;

import java.util.List;

public interface AccountServiceInt {
    public AccountDto createAccount(AccountDto accountDto);
    public AccountDto getAccountDetails(Long id);
    public List<AccountDto> getAllAccounts();
    public void closeAccount(Long id);
    public void deposit(Long id, Double amount);
    public void withdraw(Long id, Double amount);
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount);
}
