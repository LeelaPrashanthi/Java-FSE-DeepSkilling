package com.cognizant.account.repository;

import com.cognizant.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByAccountNumber(String accountNumber);
    
    List<Account> findByCustomerId(String customerId);
    
    boolean existsByAccountNumber(String accountNumber);
    
    List<Account> findByAccountType(String accountType);
} 