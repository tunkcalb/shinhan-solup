package com.example.solup.controller.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.MainPageAccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.dto.TradeHistoryReqDto;
import com.example.solup.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account APIs", description = "Account APIs")
@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @Operation(description = "accountId로 해당 계좌의 모든 거래내역 조회")
    @GetMapping("/account/history")
    public List<TradeHistoryDto> findAll(@RequestParam long accountId){
        return accountService.findAll(accountId);
    }
    
    @Operation(summary = "Account 조회", description = "userId로 해당 유저의 account 조회")
    @GetMapping("/account")
    public AccountDto findAccount(@RequestParam long userId){
        return accountService.findAccount(userId);
    }

    @Operation(description = "userId를 받아서 해당 유저의 계좌번호와 잔고를 조회")
    @GetMapping("/account/{userId}")
    public ResponseEntity<MainPageAccountDto> getMainPageAccountInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(accountService.getMainPageAccount(userId));
    }

    @Operation(description = "userId로 해당 유저의 거래내역 전체 조회")
    @GetMapping("/account/{userId}/trade-history")
    public ResponseEntity<List<TradeHistoryDto>> getTradeHistories(@PathVariable("userId")Long userId) {
        return ResponseEntity.ok(accountService.findTradeHistories(userId));
    }

    @Operation(description = "거래내역을 분류하는 api")
    @PostMapping("/account/{userId}/trade-history")
    public ResponseEntity<String> saveTradeHistory(@PathVariable("userId")Long userId,
                                              @RequestBody TradeHistoryReqDto tradeHistoryReqDto) {
        accountService.categorizeTradeHistory(userId, tradeHistoryReqDto);
        return ResponseEntity.ok("분류 완료");
    }
}
