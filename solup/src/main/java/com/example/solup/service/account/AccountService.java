package com.example.solup.service.account;

import com.example.solup.dto.*;
import com.example.solup.dto.account.AuthenticationDto;
import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import com.example.solup.exception.type.NotSameDataValueException;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.expense.FixedRepository;
import com.example.solup.repository.expense.VariableRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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

    @Value("${account.check}")
    private String accountContent;

    public List<TradeHistoryDto.Response> findAll(long accountId) {
        return tradeHistoryRepository.findByAccountId(accountId)
                .stream().map(tradeHistory -> TradeHistoryDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs((tradeHistory.getBriefs()))
                        .content(tradeHistory.getContent())
                        .category(tradeHistory.getCategory())
                        .withdraw(tradeHistory.getWithdraw())
                        .deposit(tradeHistory.getDeposit())
                        .balance(tradeHistory.getBalance())
                        .name(tradeHistory.getName())
                        .build())
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

    public void categorizeTradeHistory(Long userId, TradeHistoryCategorizeDto.Request request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        Long accountId = user.getAccount().getId();
        Long tradeHistoryId = request.getTradeHistoryId();

        TradeHistory tradeHistory = tradeHistoryRepository.findByIdAndAccountId(tradeHistoryId, accountId);
        
        // category가 2로 지출일 때 고정비와 변동비 분류 후 저장
        if (tradeHistory.getCategory() == 2 && Objects.equals(request.getExpenseType(), "Fixed")) {
            Fixed fixed = new Fixed();
            fixed.setContent(tradeHistory.getContent());
            fixed.setCategory(request.getExpenseCategory());
            fixedRepository.save(fixed);
            tradeHistory.setFixed(fixed);
        } else if (tradeHistory.getCategory() == 2 && Objects.equals(request.getExpenseType(), "Variable")) {
            Variable variable = new Variable();
            variable.setCategory(request.getExpenseCategory());
            variable.setContent(tradeHistory.getContent());
            variableRepository.save(variable);
            tradeHistory.setVariable(variable);
        }

        tradeHistoryRepository.save(tradeHistory);
    }

    public List<TradeHistoryDto.Response> findTradeHistories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        return user.getAccount().getTradeHistories().stream()
                .map(tradeHistory -> TradeHistoryDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs((tradeHistory.getBriefs()))
                        .content(tradeHistory.getContent())
                        .category(tradeHistory.getCategory())
                        .withdraw(tradeHistory.getWithdraw())
                        .deposit(tradeHistory.getDeposit())
                        .balance(tradeHistory.getBalance())
                        .name(tradeHistory.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public CategorizedDto.Response getCategorized(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        Long accountId = user.getAccount().getId();
        Integer income = tradeHistoryRepository.getCurrentMonthIncome(accountId);
        Integer fixed = tradeHistoryRepository.getCurrentMonthFixed(accountId);
        Integer variable = tradeHistoryRepository.getCurrentMonthVariable(accountId);
        Integer netProfit = income - fixed - variable;

        return CategorizedDto.Response
                .builder()
                .income(income)
                .fixed(fixed)
                .variable(variable)
                .netProfit(netProfit)
                .build();
    }

    public List<TradeHistoryCategorizeDto.Response> getCategorizedHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Long accountId = user.getAccount().getId();

        return tradeHistoryRepository.findCategorizedByAccountId(accountId).stream()
                .map(tradeHistory -> TradeHistoryCategorizeDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs(tradeHistory.getBriefs())
                        .content(tradeHistory.getContent())
                        .name(tradeHistory.getName())
                        .deposit(tradeHistory.getDeposit())
                        .withdraw(tradeHistory.getWithdraw())
                        .category(tradeHistory.getCategory())
                        .expenseType(tradeHistory.getFixed() != null ? "고정비" : "변동비")
                        .build())
                .collect(Collectors.toList());
    }

    public AuthenticationDto.Response checkAccount(AuthenticationDto.Request request) {
        Account account = accountRepository.findByNumber(request.getAccountNumber());
        LocalDateTime date = request.getDate();
        int deposit = 1;

        TradeHistory tradeHistory = TradeHistory.builder()
                .account(account)
                .date(date)
                .deposit(deposit)
                .content(accountContent)
                .build();
        tradeHistoryRepository.save(tradeHistory);

        return AuthenticationDto.Response.builder()
                .content(accountContent)
                .build();
    }

//    private LocalDateTime getCurrentMonthStart() {
//        LocalDateTime currentDate = LocalDateTime.now();
//        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
//        return yearMonth.atDay(1).atStartOfDay();
//    }
//
//    private LocalDateTime getCurrentMonthEnd() {
//        LocalDateTime currentDate = LocalDateTime.now();
//        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
//        return yearMonth.atEndOfMonth().atTime(23, 59, 59);
//    }
}
