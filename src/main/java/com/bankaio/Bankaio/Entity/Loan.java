package com.bankaio.Bankaio.Entity;

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
    private String loanStatus;
    private List<Double> instalments;
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
