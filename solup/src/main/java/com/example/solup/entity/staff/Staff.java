package com.example.solup.entity.staff;

import com.example.solup.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column
    private String account;

    // 시급
    @Column(name = "hourly_rate")
    private Integer hourlyRate;

    // 월급
    @Column
    private Integer salary;
    
    // 근무 요일 기록
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<WorkDay> workDays = new ArrayList<>();

    
    // User(=사장)과 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
