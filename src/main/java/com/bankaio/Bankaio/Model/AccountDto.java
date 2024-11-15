package com.bankaio.Bankaio.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Double balance;
    private String Type;
    private String currency;
    private Date createdAt;
    private String Status;
}
