package com.cognizant.account.service;

import com.cognizant.account.entity.Account;
import com.cognizant.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    public List<Account> getAccountsByCustomerId(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
    
    public Account createAccount(Account account) {
        // Generate unique account number if not provided
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            account.setAccountNumber(generateAccountNumber());
        }
        
        // Validate account number uniqueness
        if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
            throw new RuntimeException("Account number already exists: " + account.getAccountNumber());
        }
        
        return accountRepository.save(account);
    }
    
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        
        account.setCustomerId(accountDetails.getCustomerId());
        account.setAccountType(accountDetails.getAccountType());
        account.setBalance(accountDetails.getBalance());
        
        return accountRepository.save(account);
    }
    
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        
        accountRepository.delete(account);
    }
    
    public Account deposit(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }
    
    public Account withdraw(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }
    
    private String generateAccountNumber() {
        return "ACC" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
} 