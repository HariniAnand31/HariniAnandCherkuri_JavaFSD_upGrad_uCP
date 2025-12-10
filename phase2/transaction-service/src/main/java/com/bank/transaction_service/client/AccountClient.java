package com.bank.transaction_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/api/accounts/{accountNumber}")
    AccountDTO getAccount(@PathVariable String accountNumber);

    @PutMapping("/api/accounts/{accountNumber}/balance")
    void updateBalance(@PathVariable String accountNumber,
                       @RequestParam double balance);
}
