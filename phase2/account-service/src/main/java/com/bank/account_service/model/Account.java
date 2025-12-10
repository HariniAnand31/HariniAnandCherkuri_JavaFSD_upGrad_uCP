package com.bank.account_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String accountNumber;   // Unique account number
    private String holderName;
    private double balance;
    private boolean active = true;  // Account status (true = active)
}
