package com.bank.transaction_service.service;

import com.bank.transaction_service.client.AccountClient;
import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final AccountClient accountClient;

    public TransactionService(TransactionRepository repo, AccountClient accountClient) {
        this.repo = repo;
        this.accountClient = accountClient;
    }

    public Transaction deposit(String accountNumber, double amount) {
        Transaction txn = new Transaction();
        txn.setTransactionId(UUID.randomUUID().toString());
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setSourceAccount(accountNumber);

        try {
            accountClient.updateBalance(accountNumber, amount); // Update balance
            txn.setStatus("SUCCESS");
        } catch (Exception e) {
            txn.setStatus("FAILED");
        }
        return repo.save(txn);
    }

    public Transaction withdraw(String accountNumber, double amount) {
        Transaction txn = new Transaction();
        txn.setTransactionId(UUID.randomUUID().toString());
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setSourceAccount(accountNumber);

        try {
            accountClient.updateBalance(accountNumber, -amount); // Reduce balance
            txn.setStatus("SUCCESS");
        } catch (Exception e) {
            txn.setStatus("FAILED");
        }
        return repo.save(txn);
    }

    public Transaction transfer(String fromAccount, String toAccount, double amount) {
        Transaction txn = new Transaction();
        txn.setTransactionId(UUID.randomUUID().toString());
        txn.setType("TRANSFER");
        txn.setAmount(amount);
        txn.setSourceAccount(fromAccount);
        txn.setDestinationAccount(toAccount);

        try {
            accountClient.updateBalance(fromAccount, -amount);
            accountClient.updateBalance(toAccount, amount);
            txn.setStatus("SUCCESS");
        } catch (Exception e) {
            txn.setStatus("FAILED");
        }
        return repo.save(txn);
    }
}
