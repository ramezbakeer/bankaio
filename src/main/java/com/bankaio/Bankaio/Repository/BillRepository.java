package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    Optional<Bill> findByUser_UserIdAndBillId(Long userId, Long BillId);
    List<Bill> findAllByUser_UserId(Long userId);
}
