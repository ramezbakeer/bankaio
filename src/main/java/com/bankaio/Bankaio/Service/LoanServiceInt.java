package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Model.LoanDto;
import com.bankaio.Bankaio.Model.LoanRequestDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface LoanServiceInt {
    LoanDto createLoan(UserDto userDto, LoanRequestDto loanRequestDto);
    void processLoan(Long userId, Long loanId, Double interestRate);
    LoanDto viewLoanDetails(Long userId,Long loanId);
    List<LoanDto> viewAllLoans(Long userId);
    void updateLoanStatusAndInstallments(LoanDto loanDto, Double paymentAmount);
}
