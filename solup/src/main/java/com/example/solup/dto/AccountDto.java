package com.example.solup.dto;

import com.example.solup.entity.history.TradeHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class AccountDto {

    private Long id;

    // 계좌 번호
    private String number;

    // 잔고
    private String balance;

    private List<TradeHistory> tradeHistories = new ArrayList<>();

}
