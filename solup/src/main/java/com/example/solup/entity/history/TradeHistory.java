package com.example.solup.entity.history;

import com.example.solup.entity.account.Account;
import com.example.solup.entity.account.LoanAccount;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Living;
import com.example.solup.entity.expense.Surplus;
import com.example.solup.entity.expense.Variable;
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
public class TradeHistory {
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
    @Column
    private Integer balance;

    // 입금 지출 구분(입금 : 1, 출금 : 2)
    @Column
    private Integer category;

    // 거래점명
    @Column
    private String name;

    @Column(name = "trade_number")
    private Integer tradeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "is_categorized")
    private Boolean isCategorized;

    // 고정비, 변동비 분류 column 필요
    // 고정비일 경우 fixed와 연결해줌
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixed_id")
    private Fixed fixed;

    // 변동비일 경우 Variable과 연결해줌
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_id")
    private Variable variable;

    // 정산하기로
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "living_id")
    private Living living;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surplus_id")
    private Surplus surplus;
}
