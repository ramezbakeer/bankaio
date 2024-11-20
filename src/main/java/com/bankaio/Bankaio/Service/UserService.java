package com.bankaio.Bankaio.Service;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.*;
import org.modelmapper.ModelMapper;
import com.bankaio.Bankaio.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInt {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;
    private final BillService billService;
    private final AccountService accountService;
    private final LoanService loanService;

    private UserService(UserRepository userRepository,LoanService loanService,AccountService accountService,TransactionService transactionService,BillService billService, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper = modelMapper;
        this.transactionService=transactionService;
        this.billService=billService;
        this.accountService=accountService;
        this.loanService=loanService;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    @Override
    public UserDto viewProfile(Long userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(()->new RuntimeException("no User Found")),UserDto.class);
    }

    @Override
    public void updateProfile(Long userId, UserDto updatedDetails) {

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public TransactionDTO makeTransaction(Long accountId, Double amount, TransactionType transactionType) {
        return transactionService.createTransaction(accountId,amount,transactionType,null);
    }
    @Override
    public TransactionDTO makeTransaction(Long fromAccountId, Long toAccountId, Double amount){
        return  transactionService.createTransaction(fromAccountId,amount,TransactionType.TRANSFER,toAccountId);
    }

    @Override
    public TransactionDTO payBill(Long billId, Long accountId, Double amount) {
        BillDto billDto = billService.viewBillDetails(billId);
        AccountDto accountDto = accountService.getAccountDetails(accountId);
        // Check if the bill is already paid
        if (billDto.getStatus().equals(BillStatus.PAID)) {
            throw new RuntimeException("Bill is already paid");
        }
        // Check for sufficient funds
        if (accountDto.getBalance() < amount) {
            // Update the bill status to FAILED if applicable
            billService.updateBillStatus(billId,BillStatus.FAILED);
            throw new RuntimeException("Insufficient funds to pay the bill");
        }

        // Process the payment
        TransactionDTO transactionDTO = makeTransaction(accountId,amount,TransactionType.WITHDRAWAL);
        billService.updateBillStatus(billId,BillStatus.PAID);

        return transactionDTO;
    }

    @Override
    public TransactionDTO makeLoanPayment(Long loanId,Long accountId, Double paymentAmount) {
        LoanDto loanDto = loanService.viewLoanDetails(loanId);
        TransactionDTO transactionDTO =makeTransaction(accountId,paymentAmount,TransactionType.WITHDRAWAL);
        loanService.updateLoanStatusAndInstallments(loanDto,paymentAmount);
        return transactionDTO;
    }

    @Override
    public void requestLoan(Long userId, Double amount, int tenureMonths) {
        UserDto userDto = viewProfile(userId);
        loanService.createLoan(userDto,amount,tenureMonths);
    }

    @Override
    public void processLoan(Long loanId, Double interestRate) {
            loanService.processLoan(loanId,interestRate);
    }
}
