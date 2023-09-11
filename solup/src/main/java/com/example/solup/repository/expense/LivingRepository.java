package com.example.solup.repository.expense;

import com.example.solup.entity.expense.Living;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivingRepository extends JpaRepository<Living, Long> {
}
