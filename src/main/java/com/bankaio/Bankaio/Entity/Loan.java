package com.bankaio.Bankaio.Entity;

import com.bankaio.Bankaio.Entity.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;
    private Double principleAmount;
    private Double interestRate;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;
    private List<Double> instalments;
    private int tenureMonths;
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
