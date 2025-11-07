package model;

import exception.InvalidAmountException;
import exception.InsufficientBalanceException;

public class Account {

    // Private fields - Encapsulation
    private String accountNumber;
    private String holderName;
    private double balance;

    // Constructor - initializes new account
    public Account(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0; // Initial balance is zero
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method (synchronized for thread safety)
    public synchronized void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Amount deposited successfully. Current Balance: " + balance);
    }

    // Withdraw method (synchronized)
    public synchronized void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Available: " + balance);
        }
        balance -= amount;
        System.out.println("Amount withdrawn successfully. Current Balance: " + balance);
    }

    // Display account details
    public void showBalance() {
        System.out.println("----------------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Current Balance: " + balance);
        System.out.println("----------------------------------------");
    }
}
