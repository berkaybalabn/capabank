package com.capabank.capabank.controller;

import com.capabank.capabank.model.Account;
import com.capabank.capabank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")


public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/{customerId}")
    public Account createAccount(@PathVariable Long customerId, @RequestBody Account account) {

        return accountService.createAccount(customerId, account);


    }

    @PutMapping("/{accountId}/deposit")
    public Account deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {


        return accountService.deposit(accountId, amount);
    }
    @PutMapping("/{accountId}/withdraw")
    public Account withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount){

        return accountService.withdraw(accountId,amount);
    }
    @PutMapping("/{fromAccountId}/transfer/{toAccountId}")
    public void transfer(@PathVariable Long fromAccountId, @PathVariable Long toAccountId,@RequestParam BigDecimal amount ){
         accountService.transfer (fromAccountId,toAccountId,amount);
    }
}
