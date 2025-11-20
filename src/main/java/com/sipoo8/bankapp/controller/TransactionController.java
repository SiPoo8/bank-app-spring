package com.sipoo8.bankapp.controller;

import com.sipoo8.bankapp.entity.Transaction;
import com.sipoo8.bankapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Long accountId, @RequestParam double amount) {
        return transactionService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam Long fromAccountId,
                                @RequestParam Long toAccountId,
                                @RequestParam double amount) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }
}
