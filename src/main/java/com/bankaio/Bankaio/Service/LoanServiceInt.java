package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.LoanDto;
import com.bankaio.Bankaio.Model.UserDto;

public interface LoanServiceInt {
    public LoanDto createLoan(UserDto userDto, Double amount, int tenureMonths);
    public LoanDto processLoan(Long loanId, Double interestRate);
    public LoanDto viewLoanDetails(Long loanId);
    public void updateLoanStatusAndInstallments(LoanDto loanDto, Double paymentAmount);
}
