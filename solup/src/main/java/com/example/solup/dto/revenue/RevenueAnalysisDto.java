package com.example.solup.dto.revenue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RevenueAnalysisDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private List<RevenueDto> monthlyRevenue;
        private String monthRevenue;
    }
}
