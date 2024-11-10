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
public class TransactionDTO {
    private Long transId;

    private Double amount;

    private String transType;
    private String transStatus;
    private Date date;
}
