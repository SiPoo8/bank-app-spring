package com.sipoo8.bankapp.service;

import com.sipoo8.bankapp.entity.Account;
import com.sipoo8.bankapp.entity.Transaction;
import com.sipoo8.bankapp.repository.AccountRepository;
import com.sipoo8.bankapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import java.time.LocalDate;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setFromAccount(null);
        tx.setToAccount(account);
        tx.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(tx);
    }

    @Transactional
    public Transaction withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setFromAccount(account);
        tx.setToAccount(null);
        tx.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(tx);
    }

    @Transactional
    public Transaction transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in source account");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setFromAccount(from);
        tx.setToAccount(to);
        tx.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(tx);
    }
}
