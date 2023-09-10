package com.example.solup.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class AuthenticationDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String accountNumber;
        private LocalDateTime date;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private String content;
    }
}
