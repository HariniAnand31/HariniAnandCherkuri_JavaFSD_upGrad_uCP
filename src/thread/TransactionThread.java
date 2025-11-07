package thread;

import model.Account;
import exception.InvalidAmountException;
import exception.InsufficientBalanceException;

public class TransactionThread extends Thread {

    private Account account;
    private boolean depositOperation; // true = deposit, false = withdraw
    private double amount;

    public TransactionThread(Account account, boolean depositOperation, double amount) {
        this.account = account;
        this.depositOperation = depositOperation;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            if (depositOperation) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Thread Error: " + e.getMessage());
        }
    }
}
