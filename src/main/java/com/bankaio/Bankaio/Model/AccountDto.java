package com.bankaio.Bankaio.Model;


import com.bankaio.Bankaio.Entity.enums.AccountStatus;
import com.bankaio.Bankaio.Entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Double balance;
    private AccountType Type;
    private String currency;
    private LocalDateTime createdAt;
    private AccountStatus Status;
    private UserDto userDto;
}
