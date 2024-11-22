package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Entity.enums.LoanStatus;
import com.bankaio.Bankaio.Entity.enums.TransactionType;
import com.bankaio.Bankaio.Model.*;
import org.modelmapper.ModelMapper;
import com.bankaio.Bankaio.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInt {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TransactionServiceInt transactionService;
    private final BillServiceInt billService;
    private final AccountServiceInt accountService;
    private final LoanServiceInt loanService;
    private final NotificationServiceInt notificationService;

    public UserService(UserRepository userRepository,NotificationServiceInt notificationService,LoanServiceInt loanService,AccountServiceInt accountService,TransactionServiceInt transactionService,BillServiceInt billService, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper = modelMapper;
        this.transactionService=transactionService;
        this.billService=billService;
        this.accountService=accountService;
        this.loanService=loanService;
        this.notificationService=notificationService;
    }

    @Override
    public UserDto createUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        userRepository.save(user);
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public UserDto viewProfile(Long userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(()->new RuntimeException("no User Found")),UserDto.class);
    }

    @Override
    public void updateProfile(Long userId, UserUpdateDto updatedDetails) {
        UserDto userDto = viewProfile(userId);
        if(updatedDetails.getPhoneNumber()!=null){
            userDto.setPhoneNumber(updatedDetails.getPhoneNumber());
        }
        if(updatedDetails.getAddress()!=null){
            userDto.setAddress(updatedDetails.getAddress());
        }
        if(updatedDetails.getPassword()!=null){
            userDto.setPassword(updatedDetails.getPassword());
        }
        notificationService.sendNotification(userDto,"profile updated successfully");
        userRepository.save(modelMapper.map(userDto,User.class));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public TransactionDTO transferFund(Long userId, Long fromAccountId,TransferDto transferDto){
        notificationService
                .sendNotification(
                        viewProfile(userId)
                        ,String
                                .format(
                                        "You Just Transfer %f from Account number %d to Account Number %d"
                                        ,transferDto.getAmount()
                                        ,fromAccountId
                                        ,transferDto.getReferenceId()
                                )
                );
        notificationService
                .sendNotification(
                        accountService.getAccountDetailsById(transferDto.getReferenceId()).getUserDto()
                        ,String
                                .format(
                                        "You Just Receive %f At Account number %d From Account Number %d"
                                        ,transferDto.getAmount()
                                        ,transferDto.getReferenceId()
                                        ,fromAccountId
                                )
                );
        return  makeTransaction(userId,fromAccountId,transferDto.getAmount(),TransactionType.TRANSFER,transferDto.getReferenceId());
    }

    @Override
    public BillDto addBill(Long userId,BillCreateDto billDto) {
        UserDto userDto =  viewProfile(userId);
        BillDto createdBillDto = billService.createBill(userDto,billDto);
        notificationService.sendNotification(userDto,String.format("you add a new bill with description %s",billDto.getBillDescription()));
        return createdBillDto;
    }

    @Override
    public TransactionDTO payBill(Long userId ,Long accountId,Long billId) {
        BillDto billDto = billService.viewBillDetails(userId,billId);
        AccountDto accountDto = accountService.getAccountDetails(userId,accountId);
        // Check if the bill is already paid
        if (billDto.getStatus().equals(BillStatus.PAID)) {
            notificationService.sendNotification(billDto.getUserDto(),"You try to pay a bill which already paid");
            throw new RuntimeException("Bill is already paid");
        }
        // Check for sufficient funds
        if (accountDto.getBalance() < billDto.getAmount()) {
            // Update the bill status to FAILED if applicable
            billService.updateBillStatus(userId, billId, BillStatus.FAILED);
            notificationService.sendNotification(billDto.getUserDto(),"You try to pay a bill where no sufficient funds to pay the bill");
            throw new RuntimeException("Insufficient funds to pay the bill");
        }

        // Process the payment
        TransactionDTO transactionDTO = makeTransaction(userId, accountId, billDto.getAmount(), TransactionType.WITHDRAWAL,billId);
        billService.updateBillStatus(userId,billId, BillStatus.PAID);
        notificationService
                .sendNotification(
                        billDto.getUserDto(),String.format("your bill payment done successfully %s",billDto.getBillDescription())
                        );
        return transactionDTO;
    }

    @Override
    public TransactionDTO makeLoanPayment(Long userId, Long accountId,TransferDto transferDto) {
        LoanDto loanDto = loanService.viewLoanDetails(userId,transferDto.getReferenceId());
        AccountDto accountDto =accountService.getAccountDetails(userId,accountId);
        // Check if the bill is already paid
        if (loanDto.getLoanStatus().equals(LoanStatus.PAID)) {
            notificationService.sendNotification(loanDto.getUserDto(),"You try to pay a loan which already paid");
            throw new RuntimeException("Loan is already paid");
        }
        // Check for sufficient funds
        if (accountDto.getBalance() < transferDto.getAmount()) {
            // Update the bill status to FAILED if applicable
            notificationService.sendNotification(loanDto.getUserDto(),"You try to pay a loan where no sufficient funds to pay the loan");
            throw new RuntimeException("Insufficient funds to pay the Loan");
        }
        TransactionDTO transactionDTO =makeTransaction(userId, accountId, transferDto.getAmount(), TransactionType.WITHDRAWAL,transferDto.getReferenceId());
        loanService.updateLoanStatusAndInstallments(loanDto,transactionDTO.getAmount());
        notificationService
                .sendNotification(loanDto.getUserDto(),"your loan payment done successfully");
        return transactionDTO;
    }

    @Override
    public LoanDto requestLoan(Long userId, LoanRequestDto loanRequestDto) {
        UserDto userDto = viewProfile(userId);
        LoanDto loanDto=loanService.createLoan(userDto,loanRequestDto);
        notificationService.sendNotification(
                userDto
                ,String
                        .format(
                                "you just request a loan with %f amount and %d tenure Months"
                                ,loanRequestDto.getPrincipleAmount()
                                ,loanRequestDto.getTenureMonths()
                        )
    );
        return loanDto;
    }

    @Override
    public void processLoan(Long userId,Long loanId, Double interestRate) {
            loanService.processLoan(userId,loanId,interestRate);
    }

    @Override
    public AccountDto createAccount(Long userId, AccountCreateDto accountDto) {
        UserDto userDto = viewProfile(userId);
        return accountService.createAccount(userDto,accountDto);
    }

    @Override
    public AccountDto getAccountDetails(Long userId, Long accountId) {
        return accountService.getAccountDetails(userId,accountId);
    }

    @Override
    public List<AccountDto> getAllAccounts(Long userId) {
        return accountService.getAllAccounts(userId);
    }

    @Override
    public void closeAccount(Long userId, Long accountId) {
        accountService.closeAccount(userId,accountId);
    }

    @Override
    public List<TransactionDTO> getTransactionHistory(Long userId, Long accountId) {
        return transactionService.getTransactionHistory(userId,accountId);
    }

    @Override
    public List<LoanDto> viewAllLoans(Long userId) {
        return loanService.viewAllLoans(userId);
    }

    @Override
    public List<NotificationDto> viewNotifications(Long userId) {
        return notificationService.viewNotifications(userId);
    }

    @Override
    public void markAsReadNotification(Long userId,Long notificationId) {
        notificationService.markAsRead(userId,notificationId);
    }

    @Override
    public List<BillDto> viewAllBills(Long userId) {
        return billService.viewAllBills(userId);
    }

    @Override
    public BillDto viewBillDetails(Long userId, Long billId) {
        return billService.viewBillDetails(userId,billId);
    }

    @Override
    public LoanDto viewLoanDetails(Long userId, Long loanId) {
        return loanService.viewLoanDetails(userId,loanId);
    }

    @Override
    public TransactionDTO viewTransactionDetails(Long userId, Long accountId, Long transactionId) {
        return transactionService.viewTransactionDetails(userId,accountId,transactionId);
    }

    private TransactionDTO makeTransaction(Long userId, Long accountId, Double amount, TransactionType transactionType,Long toAccountId) {
        notificationService.sendNotification(viewProfile(userId),String.format("You Make a Transaction from Account Number %d",accountId));
        return transactionService.createTransaction(userId,accountId,amount,transactionType,toAccountId);
    }
}
