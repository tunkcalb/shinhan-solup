package com.example.solup.controller.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<TradeHistoryDto> findAll(@RequestParam long accountId){
        return accountService.findAll(accountId);
    }

    @GetMapping
    public AccountDto findAccount(@RequestParam long userId){
        return accountService.findAccount(userId);
    }
}
