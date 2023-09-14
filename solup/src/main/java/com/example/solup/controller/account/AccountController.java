package com.example.solup.controller.account;

import com.example.solup.dto.*;
import com.example.solup.dto.account.AuthenticationDto;
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
    public Response<List<TradeHistoryDto.Response>> findAll(@RequestParam long accountId){
        return new Response<>("200", "조회 성공", accountService.findAll(accountId));
    }
    
    @Operation(description = "userId로 해당 유저의 account 조회", summary = "userId로 해당 유저의 account 조회")
    @GetMapping("/account")
    public AccountDto findAccount(@RequestParam long userId){
        return accountService.findAccount(userId);
    }

    @Operation(description = "userId를 받아서 해당 유저의 계좌번호와 잔고를 조회", summary = "userId로 계좌번호와 잔고 조회")
    @GetMapping("/account/{userId}")
    public ResponseEntity<MainPageAccountDto> getMainPageAccountInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(accountService.getMainPageAccount(userId));
    }

    @Operation(description = "userId 유저의 전체 거래내역 조회", summary = "userId로 전체 거래내역 조회")
    @GetMapping("/account/{userId}/trade-history")
    public Response<List<TradeHistoryDto.Response>> getTradeHistories(@PathVariable("userId")Long userId) {
        return new Response<>("200", "조회성공", accountService.findTradeHistories(userId));
    }

    @Operation(description = "거래내역을 분류(동일한 내역은 한번에 같이 분류)", summary = "거래내역을 고정비/변동비로 분류")
    @PostMapping("/account/{userId}/trade-history")
    public ResponseEntity<String> saveTradeHistory(@PathVariable("userId")Long userId,
                                              @RequestBody TradeHistoryCategorizeDto.Request request) {
        accountService.categorizeTradeHistory(userId, request);
        return ResponseEntity.ok("분류 완료");
    }

    @Operation(description = "분류된 거래내역만 조회", summary = "분류된 거래내역만 조회")
    @GetMapping("/account/{userId}/categorized-history")
    public Response<List<TradeHistoryCategorizeDto.Response>> getCategorizedHistory(@PathVariable("userId") Long userId) {
        return new Response<>("200", "분류된 거래내역 조회 성공", accountService.getCategorizedHistory(userId));
    }

    @Operation(description = "미분류된 거래내역만 조회", summary = "미분류된 거래내역만 조회")
    @GetMapping("/account/{userId}/not-categorized")
    public Response<List<TradeHistoryDto.Response>> getNotCategorized(@PathVariable("userId")Long userId) {
        return new Response<>("200", "미분류된 거래내역 조회 성공", accountService.getNotCategorizedHistory(userId));
    }

    @Operation(description = "미분류된 거래내역 중 출금만 조회", summary = "미분류된 거래내역 중 출금만 조회")
    @GetMapping("/account/{userId}/not-categorized-withdraw")
    public Response<List<TradeHistoryDto.Response>> getNotCategorizedWithdraws(@PathVariable("userId")Long userId) {
        return new Response<>("200", "미분류된 출금내역 조회 성공", accountService.getNotCategorizedWithdraws(userId));
    }


    @Operation(description = "손익 현황 조회(이번달 매출/고정비/변동비/마진)", summary = "손익 현황(이번달 매출/고정비/변동비/마진)")
    @GetMapping("account/{userId}/monthly-result")
    public Response<CategorizedDto.Response> getCategorized(@PathVariable("userId") Long userId) {
        CategorizedDto.Response response = accountService.getCategorized(userId);
        return new Response<>("200", "조회 성공", response);
    }

    @Operation(description = "마진 정산하기", summary = "마진 정산하기")
    @PostMapping("account/{userId}/settle")
    public Response<String> settle(@PathVariable("userId")Long userId, @RequestBody SettlementDto.Request request) {
        return new Response<>("200", "이체 성공", accountService.settle(userId, request));
    }

    @Operation(description = "1원 송금")
    @PostMapping("account/check/{userId}")
    public Response<AuthenticationDto.Response> checkAccount(@PathVariable("userId")Long userId, @RequestBody AuthenticationDto.Request request){
        AuthenticationDto.Response response = accountService.checkAccount(userId, request);
        return new Response<>("201", "1원 송금 완료", response);
    }

    @Operation(description = "대출 계좌 조회", summary = "대출 계좌 조회")
    @GetMapping("account/{userId}/loan")
    public Response<LoanAccountDto.Response> getLoanAccount(@PathVariable("userId")Long userId) {
        return new Response<>("200", "대출 계좌 조회 성공", accountService.getLoanAccount(userId));
    }
}
