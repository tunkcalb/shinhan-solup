package com.example.solup.service.account;

import com.example.solup.dto.*;
import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.expense.FixedRepository;
import com.example.solup.repository.expense.VariableRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final TradeHistoryRepository tradeHistoryRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final FixedRepository fixedRepository;
    private final VariableRepository variableRepository;

    public List<TradeHistoryDto> findAll(long accountId) {
        return tradeHistoryRepository.findByAccountId(accountId)
                .stream().map(TradeHistoryDto::new)
                .collect(Collectors.toList());
    }

    public AccountDto findAccount(long userId) {
        User user = userRepository.findById(userId).get();
        return user.getAccount().toDto();
    }

    public MainPageAccountDto getMainPageAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 user를 찾을 수 없습니다."));

        return new MainPageAccountDto(user.getAccount());
    }

    public void categorizeTradeHistory(Long userId, TradeHistoryReqDto tradeHistoryReqDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setAccount(user.getAccount());
        tradeHistory.setDate(tradeHistoryReqDto.getDate());
        tradeHistory.setDeposit(tradeHistoryReqDto.getDeposit());
        tradeHistory.setWithdraw(tradeHistoryReqDto.getWithdraw());
        tradeHistory.setContent(tradeHistoryReqDto.getContent());
        tradeHistory.setBalance(tradeHistoryReqDto.getBalance());
        tradeHistory.setCategory(tradeHistoryReqDto.getCategory());
        
        // category가 2로 지출일 때 고정비와 변동비 분류 후 저장
        if (tradeHistory.getCategory() == 2 && Objects.equals(tradeHistoryReqDto.getExpenseType(), "Fixed")) {
            Fixed fixed = new Fixed();
            fixed.setContent(tradeHistoryReqDto.getContent());
            fixed.setCategory(tradeHistoryReqDto.getExpenseCategory());
            fixed.setDate(tradeHistoryReqDto.getDate());
            fixedRepository.save(fixed);
            tradeHistory.setFixed(fixed);
        } else if (tradeHistory.getCategory() == 2 && Objects.equals(tradeHistoryReqDto.getExpenseType(), "Variable")) {
            Variable variable = new Variable();
            variable.setCategory(tradeHistoryReqDto.getExpenseCategory());
            variable.setContent(tradeHistoryReqDto.getContent());
            variable.setDate(tradeHistoryReqDto.getDate());
            variableRepository.save(variable);
            tradeHistory.setVariable(variable);
        }

        tradeHistoryRepository.save(tradeHistory);
    }

    public List<TradeHistoryDto> findTradeHistories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        return user.getAccount().getTradeHistories().stream()
                .map(TradeHistoryDto::new)
                .collect(Collectors.toList());
    }

    public CategorizedDto.Response getCategorized(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        Long accountId = user.getAccount().getId();
        
        // 현재 유저의 이번달 거래내역 전체 불러옴
        List<TradeHistory> tradeHistories = tradeHistoryRepository.findAllByDateBetweenAndAccountId(getCurrentMonthStart(), getCurrentMonthEnd(), accountId);

        Integer income = 0;
        Integer fixed = 0;
        Integer variable = 0;
        // 이번달의 수익과 고정비, 변동비를 가져와서 그것을 뺀 마진을 계산하고 넘겨준다.
        for (TradeHistory tradeHistory : tradeHistories) {
            if (tradeHistory.getCategory() == 1) {
                income += tradeHistory.getDeposit();
            } else if (tradeHistory.getFixed() != null) {
                fixed += tradeHistory.getWithdraw();
            } else if (tradeHistory.getVariable() != null) {
                variable += tradeHistory.getWithdraw();
            }
        }

        Integer netProfit = income - fixed - variable;

        return CategorizedDto.Response
                .builder()
                .income(income)
                .fixed(fixed)
                .variable(variable)
                .netProfit(netProfit)
                .build();
    }

    private LocalDateTime getCurrentMonthStart() {
        LocalDateTime currentDate = LocalDateTime.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        return yearMonth.atDay(1).atStartOfDay();
    }

    private LocalDateTime getCurrentMonthEnd() {
        LocalDateTime currentDate = LocalDateTime.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        return yearMonth.atEndOfMonth().atTime(23, 59, 59);
    }
}
