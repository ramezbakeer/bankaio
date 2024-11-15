package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
