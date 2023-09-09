package com.example.solup.dto;

import com.example.solup.entity.staff.WorkDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class StaffDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String name;
        private String account;
        private Integer hourlyRate;
        private Integer salary;
        private List<String> dayOfWeek;
        private List<Integer> startTime;
        private List<Integer> endTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private String name;
        private List<WorkDay> workDays;
    }
}
