package com.example.solup.service.account;

import com.example.solup.dto.*;
import com.example.solup.dto.account.AuthenticationDto;
import com.example.solup.entity.account.Account;
import com.example.solup.entity.account.LoanAccount;
import com.example.solup.entity.history.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Living;
import com.example.solup.entity.expense.Surplus;
import com.example.solup.entity.expense.Variable;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.LoanAccountRepository;
import com.example.solup.repository.history.LoanHistoryRepository;
import com.example.solup.repository.history.TradeHistoryRepository;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final LoanAccountRepository loanAccountRepository;
    private final LoanHistoryRepository loanHistoryRepository;

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

        return tradeHistoryRepository.findByAccountIdAndIsCategorized(user.getAccount().getId(), true).stream()
                .map(tradeHistory ->
                        TradeHistoryCategorizeDto.Response.builder()
                        .id(tradeHistory.getId())
                        .tradeDate(tradeHistory.getTradeDate())
                        .tradeTime(tradeHistory.getTradeTime())
                        .briefs(tradeHistory.getBriefs())
                        .content(tradeHistory.getContent())
                        .name(tradeHistory.getName())
                        .deposit(tradeHistory.getDeposit())
                        .withdraw(tradeHistory.getWithdraw())
                        .category(tradeHistory.getCategory())
                        .expenseType(tradeHistory.getFixed() == null ? "변동비" : "고정비")
                        .expenseCategory(tradeHistory.getFixed() == null ? tradeHistory.getVariable().getCategory() : tradeHistory.getFixed().getCategory())
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
                        .balance(tradeHistory.getBalance())
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
                        .balance(tradeHistory.getBalance())
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

    public LoanAccountDto.Response getLoanAccount(Long userId) {
//        User user = userRepository.findById(userId)
//                        .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
//        LoanAccount loanAccount = user.getLoanAccount();

        LoanAccount loanAccount = loanAccountRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("해당 계좌를 찾을 수 없습니다."));

        return LoanAccountDto.Response.builder()
                .id(loanAccount.getId())
                .number(loanAccount.getNumber())
                .bankName(loanAccount.getBankName())
                .accountName(loanAccount.getAccountName())
                .withdrawalAmount(loanAccount.getWithdrawalAmount())
                .openDate(loanAccount.getOpenDate())
                .expirationDate(loanAccount.getExpirationDate())
                .loanableAmount(loanAccount.getLoanableAmount())
                .interestRate(loanAccount.getInterestRate())
                .lastDate(loanAccount.getLastDate())
                .management(loanAccount.getManagement())
                .repeatNumber(loanAccount.getRepeatNumber())
                .loanHistories(loanAccount.getLoanHistories().stream()
                        .map(loanHistory -> LoanHistoryDto.Response.builder()
                                .id(loanHistory.getId())
                                .tradeDate(loanHistory.getTradeDate())
                                .tradeTime(loanHistory.getTradeTime())
                                .briefs(loanHistory.getBriefs())
                                .content(loanHistory.getContent())
                                .category(loanHistory.getCategory())
                                .withdraw(loanHistory.getWithdraw())
                                .deposit(loanHistory.getDeposit())
                                .loanBalance(loanHistory.getLoanBalance())
                                .name(loanHistory.getName())
                                .tradeNumber(loanHistory.getTradeNumber())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
