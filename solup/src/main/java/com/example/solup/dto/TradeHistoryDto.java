package com.example.solup.dto;

import lombok.*;

import java.time.LocalDate;
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
