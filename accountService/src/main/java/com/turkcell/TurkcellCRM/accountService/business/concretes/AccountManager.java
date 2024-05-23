package com.turkcell.TurkcellCRM.accountService.business.concretes;

import com.turkcell.TurkcellCRM.accountService.business.abstracts.AccountService;
import com.turkcell.TurkcellCRM.accountService.core.Account;
import com.turkcell.TurkcellCRM.accountService.core.Order;
import com.turkcell.TurkcellCRM.accountService.dataAccess.AccountRepository;
import com.turkcell.TurkcellCRM.accountService.dataAccess.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountManager implements AccountService {
   private AccountRepository accountRepository;
   private OrderRepository orderRepository;
    @Override
    public Account add(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().toList();
    }

    @Override
    public Order addOrderToAccount(Order order) {

        return orderRepository.save(order);
    }
}
