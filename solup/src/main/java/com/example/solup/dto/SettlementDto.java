package com.example.solup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SettlementDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private Integer netProfit;
        private Integer percentage;
        private String bankName;
        private String accountNumber;
    }
}
