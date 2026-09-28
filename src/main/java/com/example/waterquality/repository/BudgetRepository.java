package com.example.waterquality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.waterquality.model.Budget;

@Repository
public interface BudgetRepository
        extends JpaRepository<Budget, Long> {

    List<Budget> findByBudgetYear(Integer budgetYear);
}