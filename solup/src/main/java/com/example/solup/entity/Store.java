package com.example.solup.entity;

import lombok.Getter;

import javax.persistence.*;

// User가 운영하는 가게 정보
@Getter
@Entity
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;
}
