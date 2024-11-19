package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Model.BillDto;

public interface BillServiceInt {
    public BillDto createBill(BillDto billDto);
    public BillDto viewBillDetails(Long billId);
    public void updateBillStatus(Long billId, BillStatus status);
}
