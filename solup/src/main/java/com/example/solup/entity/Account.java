package com.example.solup.entity;

import com.example.solup.dto.AccountDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 사용자가 등록한 계좌 정보
@Getter
@Setter
@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 은행
    @Column
    private String bank;

    // 계좌 번호
    @Column
    private String number;

    // 개설일
    @Column(name = "open_date")
    private LocalDate openDate;

    // 통화
    @Column(name = "currency_type")
    private String currencyType;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TradeHistory> tradeHistories = new ArrayList<>();

    public AccountDto toDto(){
        return AccountDto.builder()
                .id(this.id)
                .number(this.number)
                .tradeHistories(this.tradeHistories)
                .build();
    }
}
