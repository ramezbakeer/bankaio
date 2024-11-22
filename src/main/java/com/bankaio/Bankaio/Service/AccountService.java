package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Account;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.AccountStatus;
import com.bankaio.Bankaio.Model.AccountCreateDto;
import com.bankaio.Bankaio.Model.AccountDto;
import com.bankaio.Bankaio.Model.UserDto;
import com.bankaio.Bankaio.Repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements AccountServiceInt{
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public AccountService(AccountRepository accountRepository,ModelMapper modelMapper){
        this.accountRepository=accountRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public AccountDto createAccount(UserDto userDto, AccountCreateDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setStatus(AccountStatus.ACTIVE);
        account.setUser(modelMapper.map(userDto, User.class));
        return modelMapper.map(accountRepository.save(account), AccountDto.class);
    }

    @Override
    public AccountDto getAccountDetails(Long userId,Long accountId) {
        return modelMapper.map(accountRepository.findByUser_UserIdAndAccountId(userId,accountId).orElseThrow(RuntimeException::new),AccountDto.class);
    }

    @Override
    public AccountDto getAccountDetailsById(Long accountId) {
        return modelMapper.map(accountRepository.findById(accountId).orElseThrow(RuntimeException::new),AccountDto.class);
    }

    @Override
    public List<AccountDto> getAllAccounts(Long UserId) {
        return accountRepository.findAllByUser_UserId(UserId).stream().map(account -> modelMapper.map(account,AccountDto.class)).toList();
    }

    @Override
    public void closeAccount(Long userId,Long accountId) {
        AccountDto accountDto =modelMapper.map(accountRepository.findByUser_UserIdAndAccountId(userId,accountId).orElseThrow(RuntimeException::new),AccountDto.class);
        accountDto.setStatus(AccountStatus.CLOSED);
        accountRepository.save(modelMapper.map(accountDto,Account.class));
    }

    @Override
    public void deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance()<amount){
            throw new RuntimeException("NO ENOUGH MONEY");
        }else{
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        }
    }

    @Override
    public void transferFunds(Long userId,Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findByUser_UserIdAndAccountId(userId,fromAccountId).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.findById(toAccountId).orElseThrow(() -> new RuntimeException("The destination Account not found"));
        if(fromAccount.getBalance()<amount){
            throw new RuntimeException("NO ENOUGH MONEY");
        }else{
            withdraw(fromAccountId,amount);
            deposit(toAccountId,amount);
        }

    }
}
