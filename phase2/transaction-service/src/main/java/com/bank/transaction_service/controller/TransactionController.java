package com.bank.transaction_service.controller;

import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam String fromAccount,
                                @RequestParam String toAccount,
                                @RequestParam double amount) {
        return service.transfer(fromAccount, toAccount, amount);
    }
}
