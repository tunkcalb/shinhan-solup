package com.example.solup.dto.user;

import com.example.solup.entity.Store;
import com.example.solup.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignupDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        private String username;
        private String password;
        private String name;
        private String phoneNumber;

        public User toEnity(){
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .name(this.name)
                    .phoneNumber(this.phoneNumber)
                    .build();
        }
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
