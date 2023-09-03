package com.example.solup.dto;

import com.example.solup.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TradeHistoryDto {

    private Long id;

    // 거래 일시
    private LocalDateTime date;

    // 입금 금액
    private Integer deposit;

    // 출금 긍맥
    private Integer withdraw;

    // 거래 내용
    private String content;

    // 잔액
    private Integer balance;

    // 입금 지출 구분(입금 : 1, 출금 : 2)
    private Integer category;

    private long accountId;
}
