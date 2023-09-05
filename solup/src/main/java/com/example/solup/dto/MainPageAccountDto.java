package com.example.solup.dto;

import com.example.solup.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainPageAccountDto {
    private String number;
    private String balance;

    @Builder
    public MainPageAccountDto(Account account) {
        this.number = account.getNumber();
        this.balance = account.getBalance();
    }
}
