package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDto {
    private Double balance;
    private AccountType Type;
    private String currency;
}
