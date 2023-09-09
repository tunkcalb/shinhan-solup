package com.example.solup.repository.account;

import com.example.solup.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Long> {
    List<TradeHistory> findByAccountId(Long accountId);

    List<TradeHistory> findAllByDateBetweenAndAccountId(LocalDateTime startOfMonth, LocalDateTime endOfMonth, Long accountId);
}
