package com.example.solup.entity;

import com.example.solup.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;

    private String password;

    private String name;

    @Builder
    private User(long id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public UserDto toDto(){
        return UserDto.builder()
                .id(this.id)
                .username(this.username)
                .name(this.name)
                .build();
    }
}


