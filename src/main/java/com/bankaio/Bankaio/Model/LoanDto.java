package com.bankaio.Bankaio.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    private Long loanId;
    private Double principleAmount;
    private Double interestRate;
    private String loanStatus;
    private List<Double> instalments;
    private Date dueDate;
}
