package com.bankaio.Bankaio.Service;

import com.bankaio.Bankaio.Entity.Bill;
import com.bankaio.Bankaio.Entity.enums.BillStatus;
import com.bankaio.Bankaio.Model.BillDto;
import com.bankaio.Bankaio.Repository.BillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class BillService implements BillServiceInt{
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    private BillService(BillRepository billRepository,ModelMapper modelMapper){
        this.billRepository=billRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public BillDto createBill(BillDto billDto) {
        billDto.setStatus(BillStatus.PENDING);
        Bill bill = modelMapper.map(billDto,Bill.class);
        return modelMapper.map(billRepository.save(bill),BillDto.class);
    }

    @Override
    public BillDto viewBillDetails(Long billId) {
        return modelMapper
                .map(
                        billRepository
                                .findById(billId)
                                .orElseThrow(()->new RuntimeException("No bill Details available")),BillDto.class
                );
    }

    @Override
    public void updateBillStatus(Long billId, BillStatus status) {
        Bill bill = billRepository
                .findById(billId)
                .orElseThrow(()->new RuntimeException("No bill Details available"));
        bill.setStatus(status);
        billRepository.save(bill);
    }
}
