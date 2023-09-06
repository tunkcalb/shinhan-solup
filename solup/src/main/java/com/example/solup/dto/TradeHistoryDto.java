package com.example.solup.dto;

import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

    // 고정비일 경우
    private Fixed fixed;

    // 변동비일 경우
    private Variable variable;

    private String accountNumber;

    @Builder
    public TradeHistoryDto(TradeHistory tradeHistory) {
        this.id = tradeHistory.getId();
        this.date = tradeHistory.getDate();
        this.deposit = tradeHistory.getDeposit();
        this.withdraw = tradeHistory.getWithdraw();
        this.content = tradeHistory.getContent();
        this.balance = tradeHistory.getBalance();
        this.category = tradeHistory.getCategory();
        this.fixed = tradeHistory.getFixed();
        this.variable = tradeHistory.getVariable();
        this.accountNumber = tradeHistory.getAccount().getNumber();
    }
}
