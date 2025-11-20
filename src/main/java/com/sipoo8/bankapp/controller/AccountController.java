package com.sipoo8.bankapp.controller;

import com.sipoo8.bankapp.entity.Account;
import com.sipoo8.bankapp.entity.User;
import com.sipoo8.bankapp.repository.AccountRepository;
import com.sipoo8.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Account createAccount(@RequestParam Long userId, @RequestBody Account account) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            account.setOwner(userOptional.get());
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Account> getAccountsByUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            return userOptional.get().getAccounts();
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
