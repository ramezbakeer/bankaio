package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
        List<Transaction> findAllByUser_UserIdAndAccount_AccountId(Long userId, Long accountId);
        Optional<Transaction> findByUser_UserIdAndAccount_AccountIdAndTransactionId(Long userId, Long accountId, Long transactionId);
}
