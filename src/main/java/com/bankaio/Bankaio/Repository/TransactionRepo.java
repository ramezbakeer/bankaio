package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<TransactionEntity,Long> {
}
