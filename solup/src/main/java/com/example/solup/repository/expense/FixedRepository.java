package com.example.solup.repository.expense;

import com.example.solup.entity.expense.Fixed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedRepository extends JpaRepository<Fixed, Long> {
}
