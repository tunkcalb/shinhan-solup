package com.example.solup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
public class LoanAccountDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Response{
        private Long id;
        private String number;
        private String bankName;
        private String accountName;
        private Integer withdrawalAmount;
        private LocalDate openDate;
        private LocalDate expirationDate;
        private Integer loanableAmount;
        private Double interestRate;
        private LocalDate lastDate;
        private String management;
        private Integer repeatNumber;
        private List<LoanHistoryDto.Response> loanHistories;
    }
}
