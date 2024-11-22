package com.bankaio.Bankaio.Model;

import com.bankaio.Bankaio.Entity.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillCreateDto {
    private Double amount;
    private Date dueDate;
    private String billDescription;
}
