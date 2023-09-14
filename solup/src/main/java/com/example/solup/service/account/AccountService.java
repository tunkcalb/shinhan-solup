package com.example.solup.service.account;

import com.example.solup.dto.*;
import com.example.solup.dto.account.AuthenticationDto;
import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Living;
import com.example.solup.entity.expense.Surplus;
import com.example.solup.entity.expense.Variable;
import com.example.solup.exception.type.NotSameDataValueException;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.expense.FixedRepository;
import com.example.solup.repository.expense.LivingRepository;
import com.example.solup.repository.expense.SurplusRepository;
import com.example.solup.repository.expense.VariableRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final LivingRepository livingRepository;
    private final SurplusRepository surplusRepository;

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

        TradeHistory tradeHistory = tradeHistoryRepository.findFirstByAccountIdOrderByIdDesc(
                user.getAccount().getId());

        return new MainPageAccountDto(user.getAccount(), tradeHistory.getBalance(),
                user.getAccount().getBank(), user.getStoreName());
    }

    public void categorizeTradeHistory(Long userId, TradeHistoryCategorizeDto.Request request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        Long accountId = user.getAccount().getId();

        String briefs = request.getBriefs();
        String content = request.getContent();
        String expenseType = request.getExpenseType();
        String expenseCategory = request.getExpenseCategory();

        List<TradeHistory> tradeHistories = tradeHistoryRepository.findByBriefsAndContentAndCategoryAndIsCategorized(
                briefs, content, 2, false);

        if (Objects.equals(expenseType, "Fixed")) {
            Fixed fixed = new Fixed();
            fixed.setCategory(expenseCategory);
            fixed.setContent(content);
            fixedRepository.save(fixed);

            tradeHistories.forEach(tradeHistory -> {
                tradeHistory.setFixed(fixed);
                tradeHistory.setIsCategorized(true);
            });
        } else if (Objects.equals(expenseType, "Variable")) {
            Variable variable = new Variable();
            variable.setCategory(expenseCategory);
            variable.setContent(content);
            variableRepository.save(variable);

            tradeHistories.forEach(tradeHistory -> {
                tradeHistory.setIsCategorized(true);
                tradeHistory.setVariable(variable);
            });

            tradeHistoryRepository.saveAll(tradeHistories);
        }

//        Long tradeHistoryId = request.getTradeHistoryId();
//        TradeHistory tradeHistory = tradeHistoryRepository.findByIdAndAccountId(tradeHistoryId, accountId);
//
//        // category가 2로 지출일 때 고정비와 변동비 분류 후 저장
//        if (tradeHistory.getCategory() == 2 && Objects.equals(request.getExpenseType(), "Fixed")) {
//            Fixed fixed = new Fixed();
//            fixed.setContent(tradeHistory.getContent());
//            fixed.setCategory(request.getExpenseCategory());
//            fixedRepository.save(fixed);
//            tradeHistory.setFixed(fixed);
//        } else if (tradeHistory.getCategory() == 2 && Objects.equals(request.getExpenseType(), "Variable")) {
//            Variable variable = new Variable();
//            variable.setCategory(request.getExpenseCategory());
//            variable.setContent(tradeHistory.getContent());
//            variableRepository.save(variable);
//            tradeHistory.setVariable(variable);
//        }
//
//        tradeHistory.setIsCategorized(true);
//        tradeHistoryRepository.save(tradeHistory);
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

        return tradeHistoryRepository.findByAccountIdAndIsCategorized(accountId, true).stream()
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

    public List<TradeHistoryDto.Response> getNotCategorizedHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        return tradeHistoryRepository.findByAccountIdAndIsCategorized(user.getAccount().getId(),
                        false).stream()
                .map(tradeHistory -> TradeHistoryDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs(tradeHistory.getBriefs())
                        .content(tradeHistory.getContent())
                        .name(tradeHistory.getName())
                        .deposit(tradeHistory.getDeposit())
                        .withdraw(tradeHistory.getWithdraw())
                        .category(tradeHistory.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public List<TradeHistoryDto.Response> getNotCategorizedWithdraws(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        return tradeHistoryRepository.findByAccountIdAndIsCategorizedAndCategory(
                        user.getAccount().getId(), false, 2).stream()
                .map(tradeHistory -> TradeHistoryDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs(tradeHistory.getBriefs())
                        .content(tradeHistory.getContent())
                        .name(tradeHistory.getName())
                        .deposit(tradeHistory.getDeposit())
                        .withdraw(tradeHistory.getWithdraw())
                        .category(tradeHistory.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public AuthenticationDto.Response checkAccount(Long userId, AuthenticationDto.Request request) {
        Account account = accountRepository.findByNumber(request.getAccountNumber());
//        LocalDateTime date = request.getDate();
        int deposit = 1;
        int category = 1;
        String brief = "계좌이체";
        String name = "SOLUP";
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 user를 찾을 수 없습니다."));

        TradeHistory lastTrade = tradeHistoryRepository.findFirstByAccountIdOrderByIdDesc(
                account.getId());

        TradeHistory tradeHistory = TradeHistory.builder()
                .account(account)
                .balance(lastTrade.getBalance() + deposit)
                .tradeDate(LocalDate.now())
                .tradeTime(LocalTime.now())
                .deposit(deposit)
                .content(accountContent)
                .briefs(brief)
                .name(name)
                .isCategorized(true)
                .category(category)
                .build();
        tradeHistoryRepository.save(tradeHistory);

        return AuthenticationDto.Response.builder()
                .content(accountContent)
                .build();
    }

    public String settle(Long userId, SettlementDto.Request request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Integer livingExpense = request.getNetProfit() * request.getPercentage() / 100;
        Integer surplusExpense = request.getNetProfit() - livingExpense;

        TradeHistory lastTradeHistory = tradeHistoryRepository.findFirstByAccountIdOrderByIdDesc(
                user.getAccount().getId());

        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setTradeDate(LocalDate.now());
        tradeHistory.setTradeTime(LocalTime.now());
        tradeHistory.setBriefs("생활비");
        tradeHistory.setWithdraw(livingExpense);
        tradeHistory.setContent(
                "생활비 이체" + " " + request.getBankName() + " " + request.getAccountNumber());
        tradeHistory.setBalance(lastTradeHistory.getBalance() - livingExpense);
        tradeHistory.setCategory(2);
        tradeHistory.setName(request.getBankName());
        tradeHistory.setAccount(user.getAccount());

        Living living = new Living();
        living.setContent(
                "생활비 이체" + " " + request.getBankName() + " " + request.getAccountNumber());
        livingRepository.save(living);

        tradeHistory.setLiving(living);

        tradeHistoryRepository.save(tradeHistory);

        Surplus surplus = new Surplus();
        surplus.setAmount(surplusExpense);
        surplus.setDate(LocalDate.now());
        surplus.setUser(user);
        surplusRepository.save(surplus);

        return "생활비 이체" + " " + request.getBankName() + " " + request.getAccountNumber();
    }
}
