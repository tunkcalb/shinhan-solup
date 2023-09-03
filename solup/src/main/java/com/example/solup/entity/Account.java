package com.example.solup.entity;

import lombok.Getter;

import javax.persistence.*;

// 사용자가 등록한 계좌 정보
@Getter
@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 계좌 번호
    @Column
    private String number;

    // 잔액
    @Column
    private String balance;

//    @ManyToOne

}
