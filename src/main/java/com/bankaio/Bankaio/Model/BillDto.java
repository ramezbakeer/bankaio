package com.bankaio.Bankaio.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long billId;
    private Double amount;
    private Date dueDate;
    private String status;
    private String billDescription;
}
