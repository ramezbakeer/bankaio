package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.AccountDto;

import java.util.List;

public interface AccountService {
    public AccountDto createAccount(Long userId, String AccountType,String currency);
    public AccountDto getAccountDetails(Long id);
    public List<AccountDto> getAllAccounts(Long UserId);
    public void closeAccount(Long id);
    public void deposit(Long id, Double balance);
    public void withdraw(Long id, Double balance);
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount);
}
