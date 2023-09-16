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

    // 계좌번호
    @Column
    private String number;

    // 은행 이름
    @Column(name = "bank_name")
    private String bankName;

    // 계좌주명
    @Column(name = "account_name")
    private String accountName;

    // 출금가능 금액
    @Column(name = "withdrawal_amount")
    private Integer withdrawalAmount;

    // 개설일
    @Column(name = "open_date")
    private LocalDate openDate;

    // 만료일
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    //
    @Column(name = "loanable_amount")
    private Integer loanableAmount;

    // 이율
    @Column(name = "interest_rate")
    private Double interestRate;

    // 최종거래일
    @Column(name = "last_date")
    private LocalDate lastDate;

    // 관리점
    @Column
    private String management;

    // 반복횟수
    @Column(name = "repeat_number")
    private Integer repeatNumber;

    // 대출 상환기록과 연결
    @OneToMany(mappedBy = "loanAccount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LoanHistory> loanHistories = new ArrayList<>();
}
