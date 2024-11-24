package com.bankaio.Bankaio.Controller;

import com.bankaio.Bankaio.Model.*;
import com.bankaio.Bankaio.Service.UserServiceInt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class OnlineBanking {
    private final UserServiceInt userServiceInt;

     public OnlineBanking(UserServiceInt userServiceInt){
        this.userServiceInt=userServiceInt;
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> viewProfile(@PathVariable Long userId){
        UserDto userDto = userServiceInt.viewProfile(userId);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> usersSummary(){
        List<UserDto> userDtoList = userServiceInt.userSummary();
        return ResponseEntity.ok(userDtoList);
    }
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDto userDto){
        UserDto createdUserDto = userServiceInt.createUser(userDto);
        return entityWithLocation(createdUserDto.getUserId());
    }
    @PatchMapping("/users/{userId}")
    public ResponseEntity<Void> updateProfile(@PathVariable Long userId,@RequestBody UserUpdateDto updatedDetails){
        userServiceInt.updateProfile(userId,updatedDetails);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userServiceInt.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable Long userId,@PathVariable Long accountId){
        AccountDto accountDto = userServiceInt.getAccountDetails(userId,accountId);
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable Long userId){
        List<AccountDto> accountDtoList = userServiceInt.getAllAccounts(userId);
        return ResponseEntity.ok(accountDtoList);
    }
    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable Long userId,@RequestBody AccountCreateDto accountDto){
        AccountDto createdAccountDto = userServiceInt.createAccount(userId,accountDto);
        return entityWithLocation(createdAccountDto.getId());
    }

    @PatchMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long userId,@PathVariable Long accountId){
        userServiceInt.closeAccount(userId,accountId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{userId}/bills/{billId}")
    public ResponseEntity<BillDto> viewBillDetails(@PathVariable Long userId,@PathVariable Long billId){
        BillDto billDto = userServiceInt.viewBillDetails(userId,billId);
        return ResponseEntity.ok(billDto);
    }
    @GetMapping("/users/{userId}/bills")
    public ResponseEntity<List<BillDto>> viewAllBills(@PathVariable Long userId){
        List<BillDto> billDtoList = userServiceInt.viewAllBills(userId);
        return ResponseEntity.ok(billDtoList);
    }
    @PostMapping("/users/{userId}/bills")
    public ResponseEntity<Void> addBill(@PathVariable Long userId,@RequestBody BillCreateDto billDto){
      BillDto createdBillDto = userServiceInt.addBill(userId,billDto);
      return entityWithLocation(createdBillDto.getBillId());
    }
    @GetMapping("/users/{userId}/loans/{loanId}")
    public ResponseEntity<LoanDto> viewLoanDetails(@PathVariable Long userId,@PathVariable Long loanId){
        LoanDto loanDto = userServiceInt.viewLoanDetails(userId,loanId);
        return ResponseEntity.ok(loanDto);
    }
    @GetMapping("/users/{userId}/loans")
    public ResponseEntity<List<LoanDto>> viewAllLoans(@PathVariable Long userId){
        List<LoanDto> loanDtoList = userServiceInt.viewAllLoans(userId);
        return ResponseEntity.ok(loanDtoList);
    }
    @PostMapping("/users/{userId}/loans")
    public ResponseEntity<Void> requestLoan(@PathVariable Long userId,@RequestBody LoanRequestDto loanRequestDto){
        LoanDto loan = userServiceInt.requestLoan(userId,loanRequestDto);
        return entityWithLocation(loan.getLoanId());
    }
    @PatchMapping("/users/{userId}/loans/{loanId}")
    public ResponseEntity<Void> approveLoan(@PathVariable Long userId, @PathVariable Long loanId, @RequestParam Double interestRate){
        userServiceInt.processLoan(userId,loanId,interestRate);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<NotificationDto>> viewNotifications(@PathVariable Long userId){
        List<NotificationDto> notificationDtoList = userServiceInt.viewNotifications(userId);
        return ResponseEntity.ok(notificationDtoList);
    }
    @PatchMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Void> markAsReadNotification(@PathVariable Long userId,@PathVariable Long notificationId){
        userServiceInt.markAsReadNotification(userId,notificationId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{userId}/accounts/{accountId}/transactions/{action}/{transactionId}")
    public ResponseEntity<TransactionDTO> viewTransactionDetails(@PathVariable Long userId
            ,@PathVariable Long accountId
            ,@PathVariable String action
            ,@PathVariable Long transactionId){
        TransactionDTO transactionDTO;
        if("transfer".equalsIgnoreCase(action) || "pay-bill".equalsIgnoreCase(action) || "pay-loan".equalsIgnoreCase(action)){
             transactionDTO = userServiceInt.viewTransactionDetails(userId,accountId,transactionId);
        }else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
        return ResponseEntity.ok(transactionDTO);
    }
    @GetMapping("/users/{userId}/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable Long userId,@PathVariable Long accountId){
        List<TransactionDTO> transactionDTOList = userServiceInt.getTransactionHistory(userId,accountId);
        return ResponseEntity.ok(transactionDTOList);
    }
    @PostMapping("/users/{userId}/accounts/{fromAccountId}/transactions/transfer")
    public ResponseEntity<Void> transferFund(
            @PathVariable Long userId,
            @PathVariable Long fromAccountId,
            @RequestBody TransferDto transferDto) {
        TransactionDTO transactionDTO = userServiceInt.transferFund(userId, fromAccountId,transferDto);
        return entityWithLocation(transactionDTO.getTransactionId());
    }
    @PostMapping("/users/{userId}/accounts/{fromAccountId}/transactions/pay-bill")
    public ResponseEntity<Void> payBill(
            @PathVariable Long userId,
            @PathVariable Long fromAccountId,
            @RequestBody Long billId) {
        TransactionDTO transactionDTO = userServiceInt.payBill(userId, billId, fromAccountId);
        return entityWithLocation(transactionDTO.getTransactionId());
    }
    @PostMapping("/users/{userId}/accounts/{fromAccountId}/transactions/pay-loan")
    public ResponseEntity<Void> payLoan(
            @PathVariable Long userId,
            @PathVariable Long fromAccountId,
            @RequestBody TransferDto transferDto) {
        TransactionDTO transactionDTO = userServiceInt.makeLoanPayment(userId,fromAccountId,transferDto);
        return entityWithLocation(transactionDTO.getTransactionId());
    }


    private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        // Determines URL of child resource based on the full URL of the given
        // request, appending the path info with the given resource Identifier
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId)
                .toUri();

        // Return an HttpEntity object - it will be used to build the
        // HttpServletResponse
        return ResponseEntity.created(location).build();
    }
}
