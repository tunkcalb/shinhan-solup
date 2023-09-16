package com.example.solup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CategorizedDto {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Integer income;
        private Integer fixed;
        private Integer variable;
        private Integer netProfit;
    }
}
