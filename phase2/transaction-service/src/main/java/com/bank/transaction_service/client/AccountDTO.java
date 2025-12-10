package com.bank.transaction_service.client;

import lombok.Data;

@Data
public class AccountDTO {
    private String accountNumber;
    private String holderName;
    private double balance;
    private boolean active;
}
