package com.bank.account_service.service;

import com.bank.account_service.model.Account;
import com.bank.account_service.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account create(Account acc) {
        return repo.save(acc);
    }

    public Optional<Account> get(String accountNumber) {
        return repo.findById(accountNumber);
    }

    public Account updateBalance(String accountNumber, double amount) {
        Account acc = repo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setBalance(amount);
        return repo.save(acc);
    }

    public Account updateStatus(String accountNumber, boolean status) {
        Account acc = repo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setActive(status);
        return repo.save(acc);
    }
}
