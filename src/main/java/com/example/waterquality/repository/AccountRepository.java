package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}