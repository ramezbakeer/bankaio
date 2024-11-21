package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Model.BillDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface BillServiceInt {
     void createBill(UserDto userDto, BillDto billDto);
     BillDto viewBillDetails(Long userId,Long billId);
     void updateBillStatus(Long userId,Long billId, BillStatus status);
     List<BillDto> viewAllBills(Long userId);
}
