package com.example.solup.dto;

import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TradeHistoryDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Response{
        private Long id;
        private LocalDate tradeDate;
        private LocalTime tradeTime;
        private String briefs;
        private String content;
        private Integer category;
        private Integer withdraw;
        private Integer deposit;
        private Integer balance;
        private String name;
}
}
