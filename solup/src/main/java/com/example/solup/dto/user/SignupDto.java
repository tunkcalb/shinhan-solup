package com.example.solup.dto.user;

import com.example.solup.entity.Store;
import com.example.solup.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class SignupDto {
    @Data
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        private String username;
        private String password;
        private String name;
        private String phoneNumber;
        private String storeName;

        public User toEnity(){
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .name(this.name)
                    .phoneNumber(this.phoneNumber)
                    .storeName(this.storeName)
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
