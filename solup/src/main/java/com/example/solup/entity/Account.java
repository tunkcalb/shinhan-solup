package com.example.solup.entity;

import com.example.solup.dto.AccountDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 사용자가 등록한 계좌 정보
@Getter
@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 계좌 번호
    @Column
    private String number;

    // 잔고
    @Column
    private String balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TradeHistory> tradeHistories = new ArrayList<>();

    public AccountDto toDto(){
        return AccountDto.builder()
                .id(this.id)
                .number(this.number)
                .balance(this.balance)
                .tradeHistories(this.tradeHistories)
                .build();
    }
}
