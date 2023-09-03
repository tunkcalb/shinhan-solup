package com.example.solup.entity.expense;

import com.example.solup.entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 변동비
@Getter
@Entity
public class Variable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private LocalDateTime date;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
