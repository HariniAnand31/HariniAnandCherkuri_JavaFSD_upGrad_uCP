package main;

import exception.*;
import service.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        boolean exit = false;

        System.out.println("==================================");
        System.out.println("      Welcome to Bank System");
        System.out.println("==================================");

        while (!exit) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Create New Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer Funds");
                System.out.println("5. Show Balance");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume leftover newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter your full name: ");
                        String name = sc.nextLine();
                        try {
                            bankService.createAccount(name);
                        } catch (InvalidNameException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        String accNoDeposit = sc.next();
                        System.out.print("Enter Amount to Deposit: ");
                        double depAmount = sc.nextDouble();
                        try {
                            bankService.deposit(accNoDeposit, depAmount);
                        } catch (InvalidAmountException | AccountNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        String accNoWithdraw = sc.next();
                        System.out.print("Enter Amount to Withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        try {
                            bankService.withdraw(accNoWithdraw, withdrawAmount);
                        } catch (InvalidAmountException | InsufficientBalanceException | AccountNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Enter Source Account Number: ");
                        String fromAcc = sc.next();
                        System.out.print("Enter Destination Account Number: ");
                        String toAcc = sc.next();
                        System.out.print("Enter Amount to Transfer: ");
                        double transferAmount = sc.nextDouble();
                        try {
                            bankService.transfer(fromAcc, toAcc, transferAmount);
                        } catch (InvalidAmountException | InsufficientBalanceException | AccountNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        String accNoBalance = sc.next();
                        try {
                            bankService.showBalance(accNoBalance);
                        } catch (AccountNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Thank you for using Bank System!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter a valid option.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values where required.");
                sc.nextLine(); // clear invalid input
            } finally {
                // Optional: could add logging or cleanup here
            }
        }

        sc.close();
    }
}
