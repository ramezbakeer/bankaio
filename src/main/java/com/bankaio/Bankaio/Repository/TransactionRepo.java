package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {
}
