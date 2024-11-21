package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
        List<Transaction> findByUser_UserIdAndAccount_AccountId(Long userId, Long accountId);
}
