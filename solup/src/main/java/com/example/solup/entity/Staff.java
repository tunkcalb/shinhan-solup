package com.example.solup.entity;

import lombok.Getter;

import javax.persistence.*;

// 알바생 Entity
@Getter
@Entity
public class Staff {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String account;
    
    // User(=사장)과 연결
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
