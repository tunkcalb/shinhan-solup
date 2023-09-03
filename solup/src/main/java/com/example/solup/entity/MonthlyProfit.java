package com.example.solup.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 분기별 수익 Table
@Getter
@Entity
public class MonthlyProfit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 수익
    @Column
    private Integer income;

    // 비용
    @Column
    private Integer expense;

    // 순 이익
    @Column(name = "net_profit")
    private Integer netProfit;

    // 정산일
    @Column
    private LocalDateTime date;

    // User와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
