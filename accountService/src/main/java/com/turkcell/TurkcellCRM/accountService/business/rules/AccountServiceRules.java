package com.turkcell.TurkcellCRM.accountService.business.rules;

import com.turkcell.TurkcellCRM.accountService.business.messages.Messages;
import com.turkcell.TurkcellCRM.accountService.core.entities.Account;
import com.turkcell.TurkcellCRM.accountService.crossCuttingConcerns.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.accountService.dataAccess.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceRules {
    private final AccountRepository accountRepository;

    public void accountShouldBeExists(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new BusinessException(Messages.AccountErrors.ACCOUNT_NOT_FOUND);
        }
    }

    public void accountsShouldBeExist() {
        List<Account> accountList = this.accountRepository.findAll();
        if (accountList.isEmpty()) {
            throw new BusinessException(Messages.AccountErrors.ACCOUNTS_NOT_FOUND);
        }
    }

    public void accountAlreadyExists(int accountNumber){
        Optional<Account> account = this.accountRepository.getByAccountNumber(accountNumber);
        if (account.isPresent()){
            throw new BusinessException(Messages.AccountErrors.ACCOUNT_ALREADY_EXISTS);
        }
    }
}