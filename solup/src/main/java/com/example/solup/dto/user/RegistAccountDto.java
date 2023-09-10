package com.example.solup.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegistAccountDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Request {
        private String accountNumber;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private long accountId;
    }
}
