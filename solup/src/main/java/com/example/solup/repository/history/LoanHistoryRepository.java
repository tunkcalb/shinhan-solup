package com.example.solup.repository.history;

import com.example.solup.entity.history.LoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {
}
