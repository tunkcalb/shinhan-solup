package com.example.solup.entity.account;

import com.example.solup.entity.history.LoanHistory;
import com.example.solup.entity.history.TradeHistory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "loan_account")
public class LoanAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "withdrawal_amount")
    private Integer withdrawalAmount;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "loanable_amount")
    private Integer loanableAmount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Column
    private String management;

    @Column(name = "repeat_number")
    private Integer repeatNumber;

    @OneToMany(mappedBy = "loanAccount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LoanHistory> loanHistories = new ArrayList<>();
}
