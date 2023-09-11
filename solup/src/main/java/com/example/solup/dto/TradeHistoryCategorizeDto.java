package com.example.solup.dto;

import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TradeHistoryCategorizeDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private Long tradeHistoryId;
        private String expenseType;
        private String expenseCategory;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private LocalDate tradeDate;
        private LocalTime tradeTime;
        private String briefs;
        private String content;
        private String name;
        private Integer deposit;
        private Integer withdraw;
        private Integer category;
        private String expenseType;
    }
}
