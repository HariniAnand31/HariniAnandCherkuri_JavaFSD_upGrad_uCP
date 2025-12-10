package com.bank.account_service.controller;

import com.bank.account_service.model.Account;
import com.bank.account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account create(@RequestBody Account acc) {
        return service.create(acc);
    }

    @GetMapping("/{accountNumber}")
    public Optional<Account> get(@PathVariable String accountNumber) {
        return service.get(accountNumber);
    }

    @PutMapping("/{accountNumber}/balance")
    public Account updateBalance(@PathVariable String accountNumber,
                                 @RequestParam double balance) {
        return service.updateBalance(accountNumber, balance);
    }

    @PutMapping("/{accountNumber}/status")
    public Account updateStatus(@PathVariable String accountNumber,
                                @RequestParam boolean active) {
        return service.updateStatus(accountNumber, active);
    }
}
