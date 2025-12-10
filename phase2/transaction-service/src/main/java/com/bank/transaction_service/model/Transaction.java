package com.bank.transaction_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String transactionId;
    private String type;  // DEPOSIT / WITHDRAW / TRANSFER
    private double amount;
    private Date timestamp = new Date();
    private String status;  // SUCCESS / FAILED
    private String sourceAccount;
    private String destinationAccount;
}
