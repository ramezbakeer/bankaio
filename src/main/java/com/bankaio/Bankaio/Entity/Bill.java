package com.bankaio.Bankaio.Entity;

import com.bankaio.Bankaio.Entity.enums.BillStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    private Double amount;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    private String billDescription;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
    private Transaction transaction;
}
