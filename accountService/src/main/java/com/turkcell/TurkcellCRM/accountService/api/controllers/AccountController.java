package com.turkcell.TurkcellCRM.accountService.api.controllers;

import com.turkcell.TurkcellCRM.accountService.business.abstracts.AccountService;
import com.turkcell.TurkcellCRM.accountService.core.Account;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/acountservice/api/v1/accounts")
public class AccountController {
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account add(@Valid @RequestBody Account account) {
        return accountService.add(account);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll() {
        return accountService.getAll();
    }

}
