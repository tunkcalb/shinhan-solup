package com.example.solup.dto;

import com.example.solup.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainPageAccountDto {
    private String number;
    private Integer balance;
    private String bank;
    private String storeName;

    @Builder
    public MainPageAccountDto(Account account, Integer balance, String bank, String storeName) {
        this.number = account.getNumber();
        this.balance = balance;
        this.bank = bank;
        this.storeName = storeName;
    }
}
