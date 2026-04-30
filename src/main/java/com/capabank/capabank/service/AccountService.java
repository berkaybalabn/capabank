package com.capabank.capabank.service;

import com.capabank.capabank.model.Account;
import com.capabank.capabank.model.Customer;
import com.capabank.capabank.repository.AccountRepository;
import com.capabank.capabank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(Long customerId, Account account) {


        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı!"));
        account.setCustomer(foundCustomer);
        return accountRepository.save(account);
    }
    // Para Yatırma Metodu
    public Account deposit(Long accountId, BigDecimal amount) {


        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Hesap bulunamadı!"));

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance); //

        return accountRepository.save(account);

    }

}
