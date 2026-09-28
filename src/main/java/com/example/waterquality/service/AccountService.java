package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Account;
import com.example.waterquality.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // GET ALL ACCOUNTS
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // GET ACCOUNT BY ID
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found with id: " + id));
    }

    // CREATE ACCOUNT
    public Account createAccount(
            String accountCode,
            String accountName,
            String accountType,
            String description) {

        Account account = new Account();

        account.setAccountCode(accountCode);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        account.setDescription(description);

        return accountRepository.save(account);
    }

    // UPDATE ACCOUNT
    public Account updateAccount(
            Long id,
            String accountCode,
            String accountName,
            String accountType,
            String description) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found with id: " + id));

        account.setAccountCode(accountCode);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        account.setDescription(description);

        return accountRepository.save(account);
    }

    // DELETE ACCOUNT
    public void deleteAccount(Long id) {

        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }

        accountRepository.deleteById(id);
    }
}