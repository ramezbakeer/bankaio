package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Bill;
import com.bankaio.Bankaio.Model.BillDto;

import java.util.Date;

public interface BillServiceInt {
    public BillDto createBill(Long userId, Double Amount, Date dueDate);
    public BillDto viewBillDetails(Long billId);
    public void updateBillStatus(Long billId, String Status);
}
