package com.example.solup.entity;

import com.example.solup.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 알바생 Entity
@Getter
@Setter
@Entity
public class Staff {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    
    // 급여 입금 은행
    @Column
    private String bank;
    
    // 급여 입금 계좌
    @Column
    private String account;

    // 시급
    @Column(name = "hourly_rate")
    private Integer hourlyRate;
    
    // 한달 총 근무일 수
    @Column
    private Integer workDay;
    
    // 하루 근무시간
    @Column
    private Integer workHour;

    // 급여일
    @Column
    private Integer payDay;

    // 월급
    @Column
    private Integer salary;

    // User(=사장)과 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
