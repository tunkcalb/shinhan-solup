package com.example.solup.service.account;

import com.example.solup.dto.TradeHistoryDto;
import com.example.solup.entity.TradeHistory;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final TradeHistoryRepository tradeHistoryRepository;
    private final AccountRepository accountRepository;

    public List<TradeHistoryDto> findAll(long accountId) {
        return tradeHistoryRepository.findByAccountId(accountId)
                .stream().map(TradeHistory::toDto)
                .collect(Collectors.toList());
    }
}
