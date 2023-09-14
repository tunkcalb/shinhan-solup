package com.example.solup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LoanHistoryDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long id;
        private LocalDate tradeDate;
        private LocalTime tradeTime;
        private String briefs;
        private String content;
        private Integer category;
        private Integer withdraw;
        private Integer deposit;
        private Integer loanBalance;
        private String name;
        private Integer tradeNumber;
    }
}
