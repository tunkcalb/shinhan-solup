package com.example.solup.entity;

import com.example.solup.entity.expense.Surplus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String targetRevenue;

    @Column
    private String storeName;

    // 계좌 연결
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Staff> staffes = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MonthlyProfit> monthlyProfits = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Surplus> surpluses = new ArrayList<>();

    @Builder
    private User(long id, String username, String password, String name, String phoneNumber,
            String storeName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.storeName = storeName;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


