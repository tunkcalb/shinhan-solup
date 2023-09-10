package com.example.solup.repository.account;

import com.example.solup.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByNumber(String accountNumber);
}
