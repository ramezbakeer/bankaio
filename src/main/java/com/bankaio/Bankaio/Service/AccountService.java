package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Account;
import com.bankaio.Bankaio.Model.AccountDto;
import com.bankaio.Bankaio.Repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements AccountServiceInt{
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private AccountService(AccountRepository accountRepository,ModelMapper modelMapper){
        this.accountRepository=accountRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        return modelMapper.map(accountRepository.save(account),AccountDto.class);
    }

    @Override
    public AccountDto getAccountDetails(Long id) {
        return modelMapper.map(accountRepository.findById(id).orElseThrow(RuntimeException::new),AccountDto.class);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> modelMapper.map(account,AccountDto.class)).toList();
    }

    @Override
    public void closeAccount(Long id) {
        AccountDto accountDto =modelMapper.map(accountRepository.findById(id).orElseThrow(RuntimeException::new),AccountDto.class);
        accountDto.setStatus("BLOCKED");
        accountRepository.save(modelMapper.map(accountDto,Account.class));
    }

    @Override
    public void deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance()<amount){
            throw new RuntimeException("NO ENOUGH MONEY");
        }else{
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        }
    }

    @Override
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException("The destination Account not found"));
        if(fromAccount.getBalance()<amount){
            throw new RuntimeException("NO ENOUGH MONEY");
        }else{
            withdraw(fromAccountId,amount);
            deposit(toAccountId,amount);
        }

    }
}
