package service;

import exception.*;
import model.Account;
import util.AccountNumberGenerator;

import java.util.HashMap;
import java.util.Scanner;

public class BankService {

    // Collection to store all accounts
    private HashMap<String, Account> accounts = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    // Method 1: Create a new account
    public void createAccount(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Account holder name cannot be empty.");
        }

        String accountNumber = AccountNumberGenerator.generateAccountNumber(name);
        Account newAcc = new Account(accountNumber, name);
        accounts.put(accountNumber, newAcc);

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Initial Balance: " + newAcc.getBalance());
    }

    // Helper method: find account by number
    private Account getAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null) {
            throw new AccountNotFoundException("Account with number " + accNo + " not found!");
        }
        return acc;
    }

    // Method 2: Deposit
    public void deposit(String accNo, double amount)
            throws InvalidAmountException, AccountNotFoundException {
        Account acc = getAccount(accNo);
        acc.deposit(amount);
    }

    // Method 3: Withdraw
    public void withdraw(String accNo, double amount)
            throws InvalidAmountException, InsufficientBalanceException, AccountNotFoundException {
        Account acc = getAccount(accNo);
        acc.withdraw(amount);
    }

    // Method 4: Transfer
    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws InvalidAmountException, InsufficientBalanceException, AccountNotFoundException {
        Account fromAcc = getAccount(fromAccNo);
        Account toAcc = getAccount(toAccNo);

        fromAcc.withdraw(amount);  // deduct
        toAcc.deposit(amount);     // add

        System.out.println("Amount transferred successfully!");
    }

    // Method 5: Show Balance
    public void showBalance(String accNo) throws AccountNotFoundException {
        Account acc = getAccount(accNo);
        acc.showBalance();
    }

    // (Optional) Display all accounts - useful for debugging
    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found in the system.");
        } else {
            System.out.println("All existing accounts:");
            accounts.values().forEach(a ->
                    System.out.println(a.getAccountNumber() + " - " + a.getHolderName() + " - Balance: " + a.getBalance()));
        }
    }
}
