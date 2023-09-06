package com.example.solup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TradeHistoryReqDto {
    private LocalDateTime date;
    private Integer deposit;
    private Integer withdraw;
    private String content;
    private Integer balance;
    private Integer category;
    private String expenseType;
    private String expenseCategory;
    private String expenseTitle;
    private String expenseContent;
}
