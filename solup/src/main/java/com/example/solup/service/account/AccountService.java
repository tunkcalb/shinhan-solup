package com.example.solup.service.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.MainPageAccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.dto.TradeHistoryReqDto;
import com.example.solup.entity.Account;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final TradeHistoryRepository tradeHistoryRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public List<TradeHistoryDto> findAll(long accountId) {
        return tradeHistoryRepository.findByAccountId(accountId)
                .stream().map(TradeHistory::toDto)
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

    public void categorizeTradeHistory(Long accountId, TradeHistoryReqDto tradeHistoryReqDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("해당 계좌를 찾을 수 없습니다."));

        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setAccount(account);
        tradeHistory.setDate(tradeHistoryReqDto.getDate());
        tradeHistory.setDeposit(tradeHistoryReqDto.getDeposit());
        tradeHistory.setContent(tradeHistoryReqDto.getContent());
        tradeHistory.setBalance(tradeHistoryReqDto.getBalance());
        tradeHistory.setCategory(tradeHistoryReqDto.getCategory());
    }
}
