package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Loan;
import com.bankaio.Bankaio.Model.LoanDto;
import com.bankaio.Bankaio.Model.TransactionDTO;

public interface LoanServiceInt {
    public LoanDto applyForLoan(Long userId, Double amount, Double interestRate, int tenureMonths);
    public LoanDto viewLoanDetails(Long loanId);
    public TransactionDTO makeLoanPayment(Long loanId,Double paymentAmount);
}
