package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Bill;
import com.bankaio.Bankaio.Entity.User;
import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Model.BillCreateDto;
import com.bankaio.Bankaio.Model.BillDto;
import com.bankaio.Bankaio.Model.UserDto;
import com.bankaio.Bankaio.Repository.BillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillService implements BillServiceInt{
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public BillService(BillRepository billRepository,ModelMapper modelMapper){
        this.billRepository=billRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public BillDto createBill(UserDto userDto, BillCreateDto billCreateDto) {
        if (billCreateDto.getDueDate().before(new Date())) {
            throw new IllegalArgumentException("Due date must be in the future");
        }
        Bill bill = modelMapper.map(billCreateDto,Bill.class);
        bill.setStatus(BillStatus.PENDING);
        bill.setUser(modelMapper.map(userDto, User.class));
        return modelMapper.map(billRepository.save(bill),BillDto.class);
    }

    @Override
    public BillDto viewBillDetails(Long userId, Long billId) {
        return modelMapper
                .map(
                        billRepository
                                .findByUser_UserIdAndBillId(userId,billId)
                                .orElseThrow(()->new RuntimeException("No bill Details available")),BillDto.class
                );
    }

    @Override
    public void updateBillStatus(Long userId,Long billId, BillStatus status) {
        Bill bill = modelMapper.map(viewBillDetails(userId,billId),Bill.class);
        bill.setStatus(status);
        billRepository.save(bill);
    }

    @Override
    public List<BillDto> viewAllBills(Long userId) {
        return billRepository.findAllByUser_UserId(userId).stream().map(bill->modelMapper.map(bill,BillDto.class)).toList();
    }
}
