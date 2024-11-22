package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Model.BillCreateDto;
import com.bankaio.Bankaio.Model.BillDto;
import com.bankaio.Bankaio.Model.UserDto;

import java.util.List;

public interface BillServiceInt {
     BillDto createBill(UserDto userDto, BillCreateDto billDto);
     BillDto viewBillDetails(Long userId,Long billId);
     void updateBillStatus(Long userId,Long billId, BillStatus status);
     List<BillDto> viewAllBills(Long userId);
}
