package com.example.solup.service.account;

import com.example.solup.dto.AccountDto;
import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.entity.TradeHistory;
import com.example.solup.entity.User;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
