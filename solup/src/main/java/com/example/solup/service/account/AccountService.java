package com.example.solup.service.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.MainPageAccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.dto.TradeHistoryReqDto;
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

        if (Objects.equals(tradeHistoryReqDto.getExpenseType(), "Fixed")) {
            Fixed fixed = new Fixed();
            fixed.setContent(tradeHistoryReqDto.getContent());
            fixed.setCategory(tradeHistoryReqDto.getExpenseCategory());
            fixed.setDate(tradeHistoryReqDto.getDate());
            fixedRepository.save(fixed);
            tradeHistory.setFixed(fixed);
        } else if (Objects.equals(tradeHistoryReqDto.getExpenseType(), "Variable")) {
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
}
