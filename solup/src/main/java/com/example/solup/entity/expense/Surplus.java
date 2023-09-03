package com.example.solup.entity.expense;

import com.example.solup.entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 여유비용
@Getter
@Entity
public class Surplus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}