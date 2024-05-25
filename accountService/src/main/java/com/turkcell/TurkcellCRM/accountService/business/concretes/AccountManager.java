package com.turkcell.TurkcellCRM.accountService.business.concretes;

import com.turkcell.TurkcellCRM.accountService.business.abstracts.AccountService;
import com.turkcell.TurkcellCRM.accountService.business.dtos.Requests.CreateAccountRequest;
import com.turkcell.TurkcellCRM.accountService.business.dtos.Requests.UpdateAccountRequest;
import com.turkcell.TurkcellCRM.accountService.business.dtos.Responses.CreateAccountResponse;
import com.turkcell.TurkcellCRM.accountService.business.dtos.Responses.UpdateAccountResponse;
import com.turkcell.TurkcellCRM.accountService.business.rules.AccountServiceRules;
import com.turkcell.TurkcellCRM.accountService.core.entities.Account;
import com.turkcell.TurkcellCRM.accountService.crossCuttingConcerns.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.accountService.dataAccess.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountManager implements AccountService {
   private AccountRepository accountRepository;
   private AccountServiceRules accountServiceRules;
   private ModelMapperService modelMapperService;
    @Transactional
    @Override
    public CreateAccountResponse add(CreateAccountRequest createAccountRequest) {

        accountServiceRules.accountAlreadyExists(createAccountRequest.getAccountNumber());

       Account account=modelMapperService.forRequest().map(createAccountRequest,Account.class);
       Account dbAccount = accountRepository.save(account);

        return modelMapperService.forResponse().map(dbAccount, CreateAccountResponse.class);
    }

    @Transactional
    @Override
    public UpdateAccountResponse update(UpdateAccountRequest accountRequest, int id) {

        accountServiceRules.accountShouldBeExists(id);

        Account account=modelMapperService.forRequest().map(accountRequest,Account.class);
        account.setId(id);
        Account dbAccount=accountRepository.save(account);

        return modelMapperService.forResponse().map(dbAccount, UpdateAccountResponse.class);
    }

    @Override
    public List<Account> getAll() {

       accountServiceRules.accountsShouldBeExist();

        List<Account> accounts=accountRepository.findAll();

        return accounts.stream().toList();
    }

    @Transactional
    @Override
    public Account addOrderToAccount(Account account) {

       accountServiceRules.accountAlreadyExists(account.getAccountNumber());

        return accountRepository.save(account);
    }
}
