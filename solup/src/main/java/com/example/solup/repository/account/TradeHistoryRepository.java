package com.example.solup.repository.account;

import com.example.solup.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Long> {
    List<TradeHistory> findByAccountId(Long accountId);


    List<TradeHistory> findAllByDateBetweenAndAccountId(LocalDateTime startOfMonth, LocalDateTime endOfMonth, Long accountId);

    @Query("SELECT COALESCE(SUM(th.deposit), 0) FROM TradeHistory th WHERE FUNCTION('YEAR', th.date) = FUNCTION('YEAR', CURRENT_DATE) AND FUNCTION('MONTH', th.date) = FUNCTION('MONTH', CURRENT_DATE) AND th.category = 1 AND th.account.id = ?1")
    Integer getCurrentMonthIncome(Long accountId);

    @Query("SELECT COALESCE(SUM(th.withdraw), 0) FROM TradeHistory th WHERE FUNCTION('YEAR', th.date) = FUNCTION('YEAR', CURRENT_DATE) AND FUNCTION('MONTH', th.date) = FUNCTION('MONTH', CURRENT_DATE) AND th.category = 2 AND th.fixed IS NOT NULL AND th.account.id = ?1")
    Integer getCurrentMonthFixed(Long accountId);

    @Query("SELECT COALESCE(SUM(th.withdraw), 0) FROM TradeHistory th WHERE FUNCTION('YEAR', th.date) = FUNCTION('YEAR', CURRENT_DATE) AND FUNCTION('MONTH', th.date) = FUNCTION('MONTH', CURRENT_DATE) AND th.category = 2 AND th.variable IS NOT NULL AND th.account.id = ?1")
    Integer getCurrentMonthVariable(Long accountId);
}
