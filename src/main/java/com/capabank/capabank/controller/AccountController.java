package com.capabank.capabank.controller;

import com.capabank.capabank.dto.DepositRequest;
import com.capabank.capabank.dto.TransferRequest;
import com.capabank.capabank.dto.WithdrawRequest;
import com.capabank.capabank.model.Account;
import com.capabank.capabank.service.AccountService;
import jakarta.validation.Valid;
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
    public void deposit(@PathVariable Long accountId, @Valid @RequestBody DepositRequest request) {


        accountService.deposit(accountId, request.getAmount());
    }
    @PutMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @Valid @RequestBody WithdrawRequest request){

        accountService.withdraw(accountId, request.getAmount());
    }
    @PutMapping("/{fromAccountId}/transfer")
    public void transfer(@PathVariable Long fromAccountId, @Valid @RequestBody TransferRequest request) {

        // request.getToAccountId() ve request.getAmount() ile güvenli paketin içini açıyoruz
        accountService.transfer(fromAccountId, request.getToAccountId(), request.getAmount());
    }
}
