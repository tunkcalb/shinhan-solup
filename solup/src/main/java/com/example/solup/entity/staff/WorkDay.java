package com.example.solup.entity.staff;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
public class WorkDay {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 근무 요일
    @Column(name = "day_of_week")
    private String dayOfWeek;

    // 근무 시간
    // 0~24 정수로 받음(0시 ~ 24시)
    @Column(name = "start_time")
    private Integer startTime;

    @Column(name = "end_time")
    private Integer endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
