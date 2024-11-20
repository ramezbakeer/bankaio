package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Loan;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.LoanStatus;
import com.bankaio.Bankaio.Model.LoanDto;
import com.bankaio.Bankaio.Model.TransactionDTO;
import com.bankaio.Bankaio.Model.UserDto;
import com.bankaio.Bankaio.Repository.LoanRepository;
import org.modelmapper.ModelMapper;

import java.util.*;

public class LoanService implements LoanServiceInt{
    private final LoanRepository loanRepository;
    private final ModelMapper modelMapper;
    private LoanService(LoanRepository loanRepository,ModelMapper modelMapper){
        this.loanRepository=loanRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public LoanDto createLoan(UserDto userDto, Double amount, int tenureMonths) {
        Loan loan = new Loan();
        loan.setUser(modelMapper.map(userDto, User.class));
        loan.setPrincipleAmount(amount);
        loan.setTenureMonths(tenureMonths);
        loan.setLoanStatus(LoanStatus.PENDING);
        return modelMapper.map(loanRepository.save(loan),LoanDto.class);
    }

    @Override
    public LoanDto processLoan(Long loanId, Double interestRate) {
        LoanDto loanDto =viewLoanDetails(loanId);
        if (!loanDto.getLoanStatus().equals(LoanStatus.PENDING)) {
            throw new RuntimeException("Loan is already processed or invalid");
        }

        loanDto.setInterestRate(interestRate);
        loanDto.setDueDate(calculateFirstDueDate());
        loanDto.setInstalments(calculateInstallments(loanDto.getPrincipleAmount(),interestRate,loanDto.getTenureMonths()));
        loanDto.setLoanStatus(LoanStatus.APPROVED);
        Loan loan = modelMapper.map(loanDto,Loan.class);
        loanRepository.save(loan);

        return modelMapper.map(loan,LoanDto.class);
    }

    @Override
    public LoanDto viewLoanDetails(Long loanId) {
        return modelMapper.map(loanRepository.findById(loanId).orElseThrow(()->new RuntimeException("Loan Not Found")),LoanDto.class);
    }

    @Override
    public void updateLoanStatusAndInstallments(LoanDto loanDto, Double paymentAmount) {
        List<Double> installments = loanDto.getInstalments();
        double remainingPayment = paymentAmount;

        Iterator<Double> iterator = installments.iterator();
        while (iterator.hasNext() && remainingPayment > 0) {
            Double installment = iterator.next();
            if (remainingPayment >= installment) {
                remainingPayment -= installment;
                iterator.remove(); // Remove fully paid installment
            } else {
                installments.set(0, installment - remainingPayment); // Partially pay the installment
                remainingPayment = 0;
            }
        }

        if (installments.isEmpty()) {
            loanDto.setLoanStatus(LoanStatus.PAID);
        } else {
            loanDto.setLoanStatus(LoanStatus.ACTIVE);
        }
    }

    private List<Double> calculateInstallments(Double amount, Double interestRate, int tenureMonths) {
        List<Double> installments = new ArrayList<>();
        double monthlyRate = interestRate / 12 / 100;
        double installmentAmount = (amount * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -tenureMonths));
        for (int i = 0; i < tenureMonths; i++) {
            installments.add(installmentAmount);
        }
        return installments;
    }

    private Date calculateFirstDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1); // First due date is one month from now
        return calendar.getTime();
    }
}
