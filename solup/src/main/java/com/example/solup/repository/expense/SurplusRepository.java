package com.example.solup.repository.expense;

import com.example.solup.entity.expense.Surplus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurplusRepository extends JpaRepository<Surplus, Long>{
}
