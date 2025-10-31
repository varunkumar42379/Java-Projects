package org.example.service.parser;

import org.example.entity.Expense;

import java.util.Optional;

public interface ExpenseParser {
    Optional<Expense> parse(String line);
}