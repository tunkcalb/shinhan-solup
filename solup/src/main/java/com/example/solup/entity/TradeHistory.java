package com.example.solup.entity;

import com.example.solup.dto.TradeHistoryDto;
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

    // 입금 지출 구분(입금 : 1, 출금 : 2)
    @Column
    private Integer category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public TradeHistoryDto toDto(){
        return TradeHistoryDto.builder()
                .id(this.id)
                .date(this.date)
                .deposit(this.deposit)
                .withdraw(this.withdraw)
                .content(this.content)
                .balance(this.balance)
                .category(this.category)
                .accountId(this.account.getId())
                .build();
    }
}
