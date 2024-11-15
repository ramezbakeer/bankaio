package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
