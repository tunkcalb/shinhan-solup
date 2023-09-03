package com.example.solup.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 거래내역
@Entity
@Getter
public class TradeHistory {
    @Id @GeneratedValue
    private Long id;

    // 거래 일시
    @Column
    private LocalDateTime date;

    // 입금 금액
    @Column
    private Integer deposit;

    // 출금 긍맥
    @Column
    private Integer withdraw;

    // 거래 내용
    @Column
    private String content;

    // 잔액
    @Column
    private Integer balance;

    //
    @Column
    private
}
