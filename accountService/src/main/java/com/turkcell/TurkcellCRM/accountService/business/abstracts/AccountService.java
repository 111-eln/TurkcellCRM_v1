package com.turkcell.TurkcellCRM.accountService.business.abstracts;


import com.turkcell.TurkcellCRM.accountService.core.Account;
import com.turkcell.TurkcellCRM.accountService.core.Order;

import java.util.List;

public interface AccountService {
    Account add(Account account);
    List<Account> getAll();
    Order addOrderToAccount(Order order);
}
