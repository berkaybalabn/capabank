package com.capabank.capabank.service;

import com.capabank.capabank.model.Account;
import com.capabank.capabank.model.Customer;
import com.capabank.capabank.model.Transaction;
import com.capabank.capabank.model.TransactionType;
import com.capabank.capabank.repository.AccountRepository;
import com.capabank.capabank.repository.CustomerRepository;
import com.capabank.capabank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service

public class AccountService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(TransactionRepository transactionRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
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
        account.setBalance(newBalance);
        Transaction transaction = Transaction.builder()
                .toAccountId(accountId)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .timestamp(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
        return accountRepository.save(account);

    }
 public Account withdraw(Long accountId, BigDecimal amount){
     Account account = accountRepository.findById(accountId)
             .orElseThrow(() -> new RuntimeException("Hesap bulunamadı!"));


     if (account.getBalance().compareTo(amount) < 0) {
         throw new RuntimeException("Yetersiz bakiye!");
     }
     BigDecimal newBalance = account.getBalance().subtract(amount);
     account.setBalance(newBalance);
     Transaction transaction = Transaction.builder()
             .fromAccountId(accountId)
             .amount(amount)
             .type(TransactionType.WITHDRAW)
             .timestamp(LocalDateTime.now())
             .build();
     transactionRepository.save(transaction);
     return accountRepository.save(account);
 }
 public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        this.withdraw(fromAccountId,amount);
        this.deposit(toAccountId,amount);

        Transaction transaction = Transaction.builder()
                .toAccountId(toAccountId)
                .fromAccountId(fromAccountId)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .timestamp(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
 }
}
