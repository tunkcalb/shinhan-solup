package com.example.solup.dto.revenue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class RevenueAnalysisDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Map<Integer, Integer> monthlyRevenue;
        private Map<String, Integer> cardRevenue;
        private Map<String, Integer> analysis;
        private String monthRevenue;
        private String deliverySum;
        private String cash;
        private String estimatedRevenue;
        private String lastMonthExpenses;

    }
}
