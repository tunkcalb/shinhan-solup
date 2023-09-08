package com.example.solup.dto;

import com.example.solup.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Long id;

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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
