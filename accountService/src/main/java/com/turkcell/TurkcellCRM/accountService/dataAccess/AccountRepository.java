package com.turkcell.TurkcellCRM.accountService.dataAccess;

import com.turkcell.TurkcellCRM.accountService.core.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository  extends JpaRepository<Account,Integer> {
    Optional<Account> getByAccountNumber(int accountNumber);
}
