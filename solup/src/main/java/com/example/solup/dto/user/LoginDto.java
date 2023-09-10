package com.example.solup.dto.user;

import com.example.solup.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        private String username;
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private String name;
    }

}
