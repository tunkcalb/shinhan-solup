package com.example.solup.entity.history;

import com.example.solup.entity.account.LoanAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// 거래내역
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanHistory {
    @Id @GeneratedValue
    private Long id;

    // 거래일자
    @Column(name = "trade_date")
    private LocalDate tradeDate;

    // 거래시간
    @Column(name = "trade_time")
    private LocalTime tradeTime;

    // 적요
    @Column
    private String briefs;

    // 출금 긍맥
    @Column
    private Integer withdraw;

    // 입금 금액
    @Column
    private Integer deposit;

    // 거래 내용
    @Column
    private String content;

    // 잔액
    @Column(name = "loan_balance")
    private Integer loanBalance;

    // 입금 지출 구분(입금 : 1, 출금 : 2)
    @Column
    private Integer category;

    // 거래점명
    @Column
    private String name;

    @Column(name = "trade_number")
    private Integer tradeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_account_id")
    private LoanAccount loanAccount;
}
