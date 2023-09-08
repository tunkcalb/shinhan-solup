package com.example.solup.dto.sms;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class SmsDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Request {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<MessagesDto> messages;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public class Response {
        private String requestId;
        private LocalDateTime requestTime;
        private String statusCode;
        private String statusName;
    }
}
