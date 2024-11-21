package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findByUser_UserIdAndLoanId(Long userId, Long LoanId);
    List<Loan> findAllByUser_UserId(Long userId);
}
