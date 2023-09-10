package com.example.solup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StaffDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String name;
        private String bank;
        private String account;
        private Integer hourlyRate;
        private Integer workDay;
        private Integer workHour;
        private LocalDateTime payDay;
        private Integer salary;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private String name;
        private String bank;
        private String account;
        private Integer salary;
    }
}
