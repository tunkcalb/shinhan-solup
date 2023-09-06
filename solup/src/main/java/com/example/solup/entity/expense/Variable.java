package com.example.solup.entity.expense;

import com.example.solup.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// 변동비
@Getter
@Setter
@Entity
public class Variable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private LocalDateTime date;
}
