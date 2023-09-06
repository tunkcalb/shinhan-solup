package com.example.solup.controller.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.MainPageAccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.dto.TradeHistoryReqDto;
import com.example.solup.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/account/history")
    public List<TradeHistoryDto> findAll(@RequestParam long accountId){
        return accountService.findAll(accountId);
    }

    @GetMapping("/account")
    public AccountDto findAccount(@RequestParam long userId){
        return accountService.findAccount(userId);
    }

    @GetMapping("/account/{userId}")
    public ResponseEntity<MainPageAccountDto> getMainPageAccountInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(accountService.getMainPageAccount(userId));
    }

    @GetMapping("/account/{accountId}/trade-history")
    public ResponseEntity<?> getTradeHistories(@PathVariable("accountId")Long accountId) {
        return ResponseEntity.ok("G");
    }

    @PostMapping("/account/{accountId}/trade-history")
    public ResponseEntity<?> saveTradeHistory(@PathVariable("accountId")Long accountId,
                                              @RequestBody TradeHistoryReqDto tradeHistoryReqDto) {

        return ResponseEntity.ok("g");
    }
}
